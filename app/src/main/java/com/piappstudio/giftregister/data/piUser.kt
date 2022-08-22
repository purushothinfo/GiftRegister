/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val title: String? ="",
    val date: String? = null,
    val noOfPeople: Int? = 0,
    val cashAmount: Double? = null,
    val totalGold:Float? = null,
    val totalOthers:Int? = null
)