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

    @Query("SELECT * FROM EventInfo WHERE (title=: OR email=:userName) ")
   suspend fun getAllEvent(): List<EventInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(eventInfo: EventInfo)

    @Query("DELETE FROM EventInfo")
    suspend fun deleteAll()


}