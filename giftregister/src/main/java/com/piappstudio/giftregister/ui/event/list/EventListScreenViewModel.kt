/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.EventPagingSource
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pimodel.database.dao.IEventDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

// ViewModel, Model-View-ViewModel
@HiltViewModel
class EventListScreenViewModel @Inject constructor(private val piDataRepository: PiDataRepository) : ViewModel() {
   /* // mutable
    private val _lstEvents: MutableStateFlow<List<EventInfo>> = MutableStateFlow(emptyList())

    // immutable
    val lstEvents: StateFlow<List<EventInfo>> = _lstEvents*/
    val lstEvents : Flow<PagingData<EventInfo>> = Pager(PagingConfig(pageSize = 20)) {
       EventPagingSource(piDataRepository)
    }.flow

    /*fun fetchEvents() {
        viewModelScope.launch {
            piDataRepository.fetchEvents().onEach { response->
                if (response.status == Resource.Status.SUCCESS) {
                    response.data?.let { events->
                        _lstEvents.value = events
                    }
                }

            }.collect()
        }

    }*/
}