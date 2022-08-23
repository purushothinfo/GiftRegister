/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pigiftmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Model data class
 */
@Entity
data class EventInfo(
    @PrimaryKey(autoGenerate = true)
    val title: String? = null,
    val date: String? = null,
    val noOfPeople: Int? = 0,
    val cashAmount: Double? = null,
    val totalGold:Float? = null,
    val totalOthers:Int? = null
)
