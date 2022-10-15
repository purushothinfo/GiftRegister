/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel

import androidx.room.Embedded
import androidx.room.Relation

data class EventSummary(
    @Embedded val eventInfo: EventInfo,
    @Relation(parentColumn = "id", entityColumn = "eventId")
    val lstGuestInfo: List<GuestInfo>?
)