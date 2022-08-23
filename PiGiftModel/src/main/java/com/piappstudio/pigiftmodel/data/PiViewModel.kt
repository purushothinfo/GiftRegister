/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pigiftmodel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.pigiftmodel.EventInfo
import kotlinx.coroutines.launch


class PiViewModel(private val repository: GiftRepository) : ViewModel() {

   // val eventInfo: LiveData<List<EventInfo>> = repository.



    private val _liveEventData = MutableLiveData<EventInfo>(EventInfo())
    val liveEventInfo:LiveData<EventInfo> = _liveEventData

    init {
        fun insert(eventInfo: EventInfo) = viewModelScope.launch {
            repository.insert(eventInfo)
        }
    }


}


