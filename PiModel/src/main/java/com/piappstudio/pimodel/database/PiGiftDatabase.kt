/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.database.dao.IEventDao

@Database(entities = [EventInfo::class], version = 1, exportSchema = false)
abstract class PiGiftDatabase: RoomDatabase() {
    abstract fun eventDao():IEventDao
}