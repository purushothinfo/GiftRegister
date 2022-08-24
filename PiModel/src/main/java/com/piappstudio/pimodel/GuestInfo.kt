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

@Parcelize
@Entity
data class GuestInfo(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    var name:String? = null,
    var address:String?=null,
    var phone:String? =null,
    val giftValue: Double? = null,
    val giftType:GiftType =GiftType.CASH,


) : Parcelable

enum class GiftType{
    CASH,
    GOLD,
    OTHERS
}
