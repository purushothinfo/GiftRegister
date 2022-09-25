/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editguest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.GiftType
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pitheme.component.UiError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class EditGuestViewModel   @Inject constructor(private val piDataRepository: PiDataRepository) : ViewModel() {

    var selectedEventId:Long = 0
    // READ & write
    private val _guestInfo = MutableStateFlow(GuestInfo())
    // READ
    val guestInfo: StateFlow<GuestInfo> = _guestInfo

    private val _errorInfo = MutableStateFlow(GuestError())
    val errorInfo: StateFlow<GuestError> = _errorInfo

    fun updateGuestInfo(guestInfo: GuestInfo?) {
        _guestInfo.value = guestInfo?:GuestInfo()
    }
    fun updateName(name: String) {
        _guestInfo.update { it.copy(name = name) }
    }

    fun updateAddress(address: String) {
        _guestInfo.update { it.copy(address = address) }
    }

    fun updateQuantity(quantity:String) {
        _guestInfo.update { it.copy(giftValue = quantity) }
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

        if (guestInfo.giftValue == null || guestInfo.giftValue?.isBlank() == true) {
            _errorInfo.update { it.copy(addressError  = it.quantity.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(addressError  = it.quantity.copy(isError = false)) }
        }

        val updatedGuestInfo = guestInfo.copy(eventId = selectedEventId)
        viewModelScope.launch {
            piDataRepository.insert(updatedGuestInfo).onEach { response ->
                _errorInfo.update { it.copy(progress = response) }
                if (response.status == Resource.Status.SUCCESS) {
                    _guestInfo.update { GuestInfo() }
                }
            }.collect()
        }
        Timber.d("Save event information")


    }

    fun updateGiftType(giftType: GiftType) {
        _guestInfo.update { it.copy(giftType = giftType) }
    }
}

data class GuestError(
    val nameError: UiError = UiError(errorText = R.string.error_name),
    val addressError: UiError = UiError(errorText = R.string.error_address),
    val quantity: UiError = UiError(errorText = R.string.error_quantity),
    val progress : Resource<Any?> = Resource.idle()
)

