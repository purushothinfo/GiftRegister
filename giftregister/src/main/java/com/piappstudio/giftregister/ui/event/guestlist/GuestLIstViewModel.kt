/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.lifecycle.ViewModel
import com.piappstudio.pigiftmodel.GiftType
import com.piappstudio.pigiftmodel.GuestInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GuestLIstViewModel @Inject constructor(): ViewModel() {
    private val _lstGuest: MutableStateFlow<List<GuestInfo>> = MutableStateFlow(emptyList())

    val lstGuest:StateFlow<List<GuestInfo>> = _lstGuest

    init {
        Timber.d("init part")
        fetchGuest()

    }

    private fun fetchGuest(){
        val guests = mutableListOf<GuestInfo>()

        val calender = Calendar.getInstance()
        calender.time.time = Date().time

      val phone=956668991
        var cash=100
        var gold=2
        var others =5
        for (index in 1..100) {
            calender.add(Calendar.DATE, 1)
            val updatedPhone  = phone+index

            val guestInfo=GuestInfo(
                name = "Gundu$index ",
                address = "Arumbakkam",
                phone = updatedPhone.toString()

            )
            guests.add(guestInfo)

        }
          _lstGuest.value =guests


        }

      fun onClickAdd(){
          Timber.d("onclick is called")
      }
     fun onClickEdit(info: GuestInfo){
         Timber.d("called name= ${info.name},address = ${info.address}, phone = ${info.phone}")
     }

    }

