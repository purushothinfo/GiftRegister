/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editguest

import androidx.lifecycle.ViewModel
import com.piappstudio.giftregister.ui.event.guestlist.GuestLIstViewModel
import com.piappstudio.pigiftmodel.GuestInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class EditGuestViewModel   @Inject constructor() : ViewModel()  {
    private val _lstEdit: MutableStateFlow<List<GuestInfo>> = MutableStateFlow(emptyList())

    val lstEdit:StateFlow<List<GuestInfo>> = _lstEdit

    init {
        Timber.d("init part")
        fetchGuest()
    }
    private fun fetchGuest() {
        val edits = mutableListOf<GuestInfo>()

    }

}
