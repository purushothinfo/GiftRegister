/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.pimodel.EventSummary
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
    fun fetchEventList() {
        viewModelScope.launch {
            _eventList.update { piDataRepository.fetchEvents() }
        }
    }
}