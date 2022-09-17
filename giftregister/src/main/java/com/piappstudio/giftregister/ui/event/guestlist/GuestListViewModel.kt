/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.lifecycle.ViewModel
import com.piappstudio.pimodel.GuestInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GuestListViewModel @Inject constructor() : ViewModel() {

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

    private fun fetchGuest() {
        val guests = mutableListOf<GuestInfo>()

        val calender = Calendar.getInstance()
        calender.time.time = Date().time


        val phone = 956668991
        for (index in 1..100) {
            calender.add(Calendar.DATE, 1)
            val updatedPhone = phone + index

            val guestInfo = GuestInfo(
                name = "Gundu$index ",
                address = "Arumbakkam",
                phone = updatedPhone.toString()


            )
            guests.add(guestInfo)

        }
        _lstGuest.value = guests


    }

    fun onClickAdd() {
        Timber.d("onclick is called")
    }

}

