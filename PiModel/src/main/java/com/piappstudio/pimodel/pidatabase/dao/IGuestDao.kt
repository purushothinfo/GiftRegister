/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.pidatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import com.piappstudio.pimodel.GuestInfo
@Dao
interface IGuestDao {
     @Insert
    suspend fun insert(guestInfo: GuestInfo):Long

}