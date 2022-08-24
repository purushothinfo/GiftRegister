/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel

data class GuestInfo(
    var name:String? = null,
    var address:String?=null,
    var phone:String? =null,
    val giftValue: Double? = null,
    val giftType:GiftType =GiftType.CASH,


)

enum class GiftType{
    CASH,
    GOLD,
    OTHERS
}
