/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInfo::class], version = 2, exportSchema = false)
abstract class GiftDatabase:RoomDatabase() {
    abstract fun userInfo(): UserInfo

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GiftDatabase? = null

        fun getDatabase(context: Context): GiftDatabase {

            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GiftDatabase::class.java,
                    "GiftDatabase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}