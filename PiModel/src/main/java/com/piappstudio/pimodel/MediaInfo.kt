/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MediaInfo( @PrimaryKey(autoGenerate = true)
                      val id:Long = 0,
                      val path:String,
                      val eventId:Long?=null,
                      val guestId:Long?=null)
