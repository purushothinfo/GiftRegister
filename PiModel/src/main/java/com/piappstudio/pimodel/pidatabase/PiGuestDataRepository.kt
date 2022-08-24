/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.pidatabase

import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pimodel.pidatabase.dao.IGuestDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiGuestDataRepository @Inject constructor(private val guestDao: IGuestDao) {
    suspend fun insert(guestInfo: GuestInfo){
        guestDao.insert(guestInfo)
    }
}