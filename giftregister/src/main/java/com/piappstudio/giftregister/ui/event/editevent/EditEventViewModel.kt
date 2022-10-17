/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.Constant
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pitheme.component.UiError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        var eventInfo = _eventInfo.value.copy()
        if (eventInfo.title == null || eventInfo.title?.isBlank() == true) {
            _errorInfo.update { it.copy(nameError = it.nameError.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(nameError = it.nameError.copy(isError = false)) }
        }

        var isValidDate = false
        try {
            eventInfo.date?.let {
                val eventDate = Constant.PiFormat.eventInputFormat.parse(it)
                isValidDate = true
                eventInfo = eventInfo.copy(date = Constant.PiFormat.orderDisplay.format(eventDate))

            }
        }catch (ex:Exception) {
            Timber.e(ex)
        }


        if (!isValidDate || eventInfo.date == null || eventInfo.date?.isBlank() == true) {
            _errorInfo.update { it.copy(dateError = it.dateError.copy(isError = true)) }
            return
        } else {
            _errorInfo.update { it.copy(dateError = it.dateError.copy(isError = false)) }
        }
        viewModelScope.launch {
            if (eventInfo.id != 0L) {
                // Perform update operation
                piDataRepository.update(eventInfo).onEach { response ->
                    _errorInfo.update { it.copy(progress = response) }
                    if (response.status == Resource.Status.SUCCESS) {
                        _eventInfo.update { EventInfo() }
                    }
                }.collect()
            } else {
                piDataRepository.insert(eventInfo).onEach { response ->
                    _errorInfo.update { it.copy(progress = response) }
                    if (response.status == Resource.Status.SUCCESS) {
                        _eventInfo.update { EventInfo() }
                    }
                }.collect()
            }

            Timber.d("Save event information")
        }
    }
}

data class EventError(
    val nameError: UiError = UiError(errorText = R.string.error_name),
    val dateError: UiError = UiError(errorText = R.string.error_date),
    val progress :Resource <Any?> = Resource.idle()
)


