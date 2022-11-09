/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.EventSummary
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pinavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

// ViewModel, Model-View-ViewModel
@HiltViewModel
class EventListScreenViewModel @Inject constructor(private val piDataRepository: PiDataRepository, val navManager: NavManager) : ViewModel() {

    private val _eventList = MutableStateFlow(emptyList<EventSummary>())
    val eventList:StateFlow<List<EventSummary>> = _eventList

    private val _progress = MutableStateFlow(Resource.Status.NONE)
    val progress:StateFlow<Resource.Status> = _progress

    fun fetchEventList() {
        viewModelScope.launch {
            _eventList.update { piDataRepository.fetchEvents() }
        }
    }

    fun delete(eventSummary: EventSummary) {
        viewModelScope.launch {
            piDataRepository.delete(eventSummary).onEach { result->

                if (result.status == Resource.Status.SUCCESS) {
                    fetchEventList()
                    _progress.update { Resource.Status.SUCCESS }
                } else {
                    _progress.update { result.status }
                }

            }.collect()
        }

    }
}