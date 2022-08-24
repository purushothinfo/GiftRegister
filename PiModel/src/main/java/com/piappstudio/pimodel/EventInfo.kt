/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/***
 * Model data class
 * CURD = Create, Update, Read, Delete
 */
@Parcelize
@Entity
data class EventInfo(
    @PrimaryKey (autoGenerate = true)
    val id:Long = 0,
    val title: String? = null,
    val date: String? = null,
    val noOfPeople: Int? = 0,
    val cashAmount: Double? = null,
    val totalGold:Float? = null,
    val totalOthers:Int? = null
) : Parcelable
