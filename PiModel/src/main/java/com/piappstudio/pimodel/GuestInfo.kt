/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity
data class GuestInfo(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    var name:String? = null,
    var address:String?=null,
    var phone:String? =null,
    val giftValue: String? = null,
    val giftType:GiftType =GiftType.CASH,
    val eventId:Long = 0
) {
    fun displayGiftValue():String? {
        if (giftType == GiftType.CASH) {
            val currency: Currency = Currency.getInstance(Locale.getDefault())
            val symbol: String = currency.symbol
            return "$symbol $giftValue"

        }
        return giftValue
    }
}

enum class GiftType{
    CASH,
    GOLD,
    OTHERS
}
