/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pigiftmodel.data

import androidx.room.*
import com.piappstudio.pigiftmodel.EventInfo


@Dao
interface EventDao {

    @Query("SELECT * FROM EventInfo ")
   suspend fun getAlphabetized(): List<EventInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(eventInfo: EventInfo)

    @Delete
    suspend fun deleteAll(eventInfo: EventInfo)

    @Update
    suspend fun update(eventInfo: EventInfo): Int


}