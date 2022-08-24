/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.pidatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pimodel.pidatabase.dao.IGuestDao

@Database(entities = [GuestInfo::class,], version = 2, exportSchema = false)
abstract class PiGuestDatabase:RoomDatabase() {
    abstract fun guestDao():IGuestDao
}