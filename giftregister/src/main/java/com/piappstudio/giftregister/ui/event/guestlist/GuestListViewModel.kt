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

@HiltViewModel
class GuestListViewModel @Inject constructor(
    private val piDataRepository: PiDataRepository,
    val navManager: NavManager
) : ViewModel() {

    var selectedEventId: Long = 0
    private val _lstGuest: MutableStateFlow<List<GuestInfo>> = MutableStateFlow(emptyList())
    val lstGuest: StateFlow<List<GuestInfo>> = _lstGuest
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
                    }
                }

            }.collect()

        }

    }

    fun onClickAdd() {
        Timber.d("onclick is called")
    }

}

