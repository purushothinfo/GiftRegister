/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel

/***
 * Model data class
 */
data class EventInfo(
    val title: String? = null,
    val date: String? = null,
    val noOfPeople: Int? = 0,
    val cashAmount: Double? = null,
    val totalGold:Float? = null,
    val totalOthers:Int? = null
)
