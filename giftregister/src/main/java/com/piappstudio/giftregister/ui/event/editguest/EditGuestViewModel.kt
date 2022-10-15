/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editguest

import androidx.lifecycle.ViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pitheme.component.UiError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class EditGuestViewModel   @Inject constructor() : ViewModel() {

    // READ & write
    private val _guestInfo = MutableStateFlow(GuestInfo())
    // READ
    val guestInfo: StateFlow<GuestInfo> = _guestInfo

    private val _errorInfo = MutableStateFlow(GuestError())
    val errorInfo: StateFlow<GuestError> = _errorInfo

    fun updateName(name: String) {
        _guestInfo.update { it.copy(name = name) }
    }

    fun updateAddress(address: String) {
        _guestInfo.update { it.copy(address = address) }
    }

    fun onClickSubmit() {
        val guestInfo = _guestInfo.value
        if (guestInfo.name == null || guestInfo.name?.isBlank() == true) {
            _errorInfo.update { it.copy(nameError = it.nameError.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(nameError = it.nameError.copy(isError = false)) }
        }

        if (guestInfo.address == null || guestInfo.address?.isBlank() == true) {
            _errorInfo.update { it.copy(addressError  = it.addressError.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(addressError  = it.addressError.copy(isError = false)) }
        }

        //TODO: Save event information
        Timber.d("Save event information")


    }
}

data class GuestError(
    val nameError: UiError = UiError(errorText = R.string.error_name),
    val addressError: UiError = UiError(errorText = R.string.error_address)
)

