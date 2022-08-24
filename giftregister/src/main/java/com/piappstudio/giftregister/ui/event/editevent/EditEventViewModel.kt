/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pitheme.component.UiError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(private val piDataRepository: PiDataRepository) : ViewModel() {
    // Model
    private val _eventInfo = MutableStateFlow(EventInfo())
    val eventInfo: StateFlow<EventInfo> = _eventInfo

    private val _errorInfo = MutableStateFlow(EventError())
    val errorInfo: StateFlow<EventError> = _errorInfo


    fun updateTitle(name: String) {
        _eventInfo.update { it.copy(title = name) }
    }

    fun updateDate(date: String) {
        _eventInfo.update { it.copy(date = date) }
    }

    fun onClickSubmit() {
        val eventInfo = _eventInfo.value
        if (eventInfo.title == null || eventInfo.title?.isBlank() == true) {
            _errorInfo.update { it.copy(nameError = it.nameError.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(nameError = it.nameError.copy(isError = false)) }
        }

        if (eventInfo.date == null || eventInfo.date?.isBlank() == true) {
            _errorInfo.update { it.copy(dateError = it.dateError.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(dateError = it.dateError.copy(isError = false)) }
        }
        viewModelScope.launch {
            piDataRepository.insert(eventInfo)
            Timber.d("Save event information")
        }
    }
}

data class EventError(
    val nameError: UiError = UiError(errorText = R.string.error_name),
    val dateError: UiError = UiError(errorText = R.string.error_date)
)


