/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pinavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class GuestListState(val lstGuest:List<GuestInfo>, val filterOption: SearchOption)

@HiltViewModel
class GuestListViewModel @Inject constructor(
    private val piDataRepository: PiDataRepository,
    val navManager: NavManager
) : ViewModel() {

    var selectedEventId: Long = 0


    private val _lstGuest: MutableStateFlow<List<GuestInfo>> = MutableStateFlow(emptyList())

    private val _guestListState = MutableStateFlow(GuestListState(lstGuest = emptyList(), filterOption = SearchOption()))
    val guestListState:StateFlow<GuestListState> = _guestListState


    private val _selectedGiftInfo: MutableStateFlow<GuestInfo> = MutableStateFlow(GuestInfo())
    val selectedGiftInfo: StateFlow<GuestInfo> = _selectedGiftInfo
    fun updateSelectedGiftInfo(guestInfo: GuestInfo?) {
        _selectedGiftInfo.update { guestInfo ?: GuestInfo() }
    }

    fun fetchGuest() {
        viewModelScope.launch (Dispatchers.IO) {
            Timber.d("Fetching event for $selectedEventId")
            piDataRepository.fetchGuest(selectedEventId).onEach { result ->
                Timber.d("Status ${result.status}")
                if (result.status == Resource.Status.SUCCESS) {
                    result.data?.let { guestList ->
                        Timber.d("Data size: ${guestList.size}")
                        _lstGuest.value = guestList
                        _guestListState.update { it.copy(lstGuest = guestList) }
                    }
                }

            }.collect()

        }

    }

    fun updateSearchText(text: String) {
        _guestListState.update { it.copy(filterOption = it.filterOption.copy(text = text)) }
        val lstFiltered = _lstGuest.value.filter { it.name?.contains(text, true) == true
                || it.address?.contains(text, true) == true}
        _guestListState.update { it.copy(lstGuest = lstFiltered) }
    }
}

data class SearchOption(val text:String? = null)

