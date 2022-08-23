/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pigiftmodel.data

import com.piappstudio.pigiftmodel.EventInfo


class GiftRepository(private val eventDao: EventDao) {

    suspend fun eventInfo():List<EventInfo> {
        return eventDao.getAllEvent()
    }
    suspend fun insert(eventInfo: EventInfo) {
        eventDao.insert(eventInfo)
    }


}