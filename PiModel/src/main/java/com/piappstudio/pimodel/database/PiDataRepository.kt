/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database

import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.database.dao.IEventDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiDataRepository @Inject constructor(private val eventDao:IEventDao) {

    suspend fun insert(eventInfo: EventInfo) {
        eventDao.insert(eventInfo)
    }
}