/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.lifecycle.ViewModel
import com.piappstudio.pimodel.EventInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.util.*
import javax.inject.Inject

// ViewModel, Model-View-ViewModel
@HiltViewModel
class EventListScreenViewModel @Inject constructor() : ViewModel() {
    // mutable
    private val _lstEvents: MutableStateFlow<List<EventInfo>> = MutableStateFlow(emptyList())

    // immutable
    val lstEvents: StateFlow<List<EventInfo>> = _lstEvents

    init {
        Timber.d("init part")
       fetchEvents()

    }

    private fun fetchEvents() {
        // TODO: To fetch data from database

        // TODO: Remove after, dummy
        val events = mutableListOf<EventInfo>()

        val calender = Calendar.getInstance()
        calender.time.time = Date().time

        var default = 100
        var cash = 50000.00
        var gold = 2.0f
        var others = 30

        for (index in 1..100) {
            calender.add(Calendar.DATE, 1)

            default += 10
            cash += 10000.00
            gold+= 1.0f
            others += 5
            val eventInfo = EventInfo(
                title = "Event $index",
                date = calender.time.toGMTString(),
                noOfPeople = default,
                cashAmount = cash,
                totalGold = gold,
                totalOthers = others

            )

            events.add(eventInfo)
        }

        _lstEvents.value = events

    }
}