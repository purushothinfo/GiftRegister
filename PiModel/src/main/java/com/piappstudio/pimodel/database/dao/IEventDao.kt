/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.piappstudio.pimodel.EventInfo

@Dao
interface IEventDao {
    @Insert
    suspend fun insert(eventInfo: EventInfo):Long

    @Query ("SELECT * FROM eventinfo")
    suspend fun fetchEvents():List<EventInfo>?
}