/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pinavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GuestListViewModel @Inject constructor(private val piDataRepository: PiDataRepository, val navManager: NavManager) : ViewModel() {

    var selectedEventId: Long = 0
    private val _lstGuest: MutableStateFlow<List<GuestInfo>> = MutableStateFlow(emptyList())
    val lstGuest: StateFlow<List<GuestInfo>> = _lstGuest
    private val _selectedGiftInfo:MutableStateFlow<GuestInfo> = MutableStateFlow(GuestInfo())
    val selectedGiftInfo: StateFlow<GuestInfo> = _selectedGiftInfo

    fun updateSelectedGiftInfo(guestInfo: GuestInfo?) {
        _selectedGiftInfo.update { guestInfo?:GuestInfo()  }
    }

    init {
        Timber.d("init part")
        fetchGuest()
    }

    fun fetchGuest() {
        viewModelScope.launch {
           val lstGuest = piDataRepository.fetchGuest(selectedEventId)
            _lstGuest.update { lstGuest }
        }

    }

    fun onClickAdd() {
        Timber.d("onclick is called")
    }

}

