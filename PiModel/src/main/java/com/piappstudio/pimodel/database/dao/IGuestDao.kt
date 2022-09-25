/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.piappstudio.pimodel.GuestInfo

@Dao
interface IGuestDao {
    @Insert
    suspend fun insert(guestInfo: GuestInfo):Long

    @Query("SELECT * FROM guestinfo where eventId = :eventId")
    suspend fun fetchGuest(eventId:Long):List<GuestInfo>
}