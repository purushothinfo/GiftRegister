/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database

import android.util.Log
import com.piappstudio.pimodel.*
import com.piappstudio.pimodel.error.PIError
import com.piappstudio.pimodel.database.dao.IEventDao
import com.piappstudio.pimodel.database.dao.IGuestDao
import com.piappstudio.pimodel.database.dao.IMediaDao
import com.piappstudio.pimodel.error.ErrorCode.DATABASE_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiDataRepository @Inject constructor(private val eventDao:IEventDao,
                                           private val guestDao: IGuestDao,
                                           private val mediaDao:IMediaDao
) {

    suspend fun insert(eventInfo: EventInfo): Flow<Resource<Long?>> {
        return makeSafeApiCall {
            eventDao.insert(eventInfo)
        }
    }

    suspend fun fetchEvents(): List<EventSummary> {
        return eventDao.fetchEvents()
    }

    suspend fun insert(guestInfo: GuestInfo, lstMediaInfo: List<MediaInfo>): Flow<Resource<Long?>> {
        return makeSafeApiCall {
            val guestId = guestDao.insert(guestInfo)
            for (mediaInfo in lstMediaInfo) {
                val updatedMediaInfo = mediaInfo.copy(guestId = guestId)
                mediaDao.insert(updatedMediaInfo)
            }
            guestId

        }
    }

    suspend fun delete(mediaInfo: MediaInfo):Flow<Resource<Unit?>> {
        return makeSafeApiCall {
            mediaDao.delete(mediaInfo)
        }
    }


    suspend fun fetchGuest(eventId:Long): Flow<Resource<List<GuestInfo>?>> {
        return makeSafeApiCall {
            guestDao.fetchGuest(eventId)
        }
    }

    suspend fun fetchGuestMedia(guestId:Long): Flow<Resource<List<MediaInfo>?>> {
        return makeSafeApiCall {
            mediaDao.fetchGuestMedias(guestId = guestId)
        }
    }

    private suspend fun <T> makeSafeApiCall(api: suspend () -> T?) = flow {
        try {
            emit(Resource.loading())
            val response = api.invoke()
            // TODO: Remove it
            kotlinx.coroutines.delay(2000)
            emit(Resource.success(response))
        } catch (ex: Exception) {
            Timber.e(ex)
            emit(Resource.error(error = PIError(code = DATABASE_ERROR, message = ex.message)))
        }
    }
}