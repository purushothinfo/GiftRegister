/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database

import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.error.PIError
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.database.dao.IEventDao
import com.piappstudio.pimodel.error.ErrorCode.DATABASE_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiDataRepository @Inject constructor(private val eventDao:IEventDao) {

    suspend fun insert(eventInfo: EventInfo): Flow<Resource<Long?>> {
        return makeSafeApiCall {
            eventDao.insert(eventInfo)
        }
    }

    suspend fun fetchEvents():Flow<Resource<List<EventInfo>?>> {
        return makeSafeApiCall {
            eventDao.fetchEvents()
        }
    }


    private suspend fun <T> makeSafeApiCall(api: suspend () -> T?) = flow {
        try {
            emit(Resource.loading())
            val response = api.invoke()
            kotlinx.coroutines.delay(2000)
            emit(Resource.success(response))
        } catch (ex: Exception) {
            Timber.e(ex)
            emit(Resource.error(error = PIError(code = DATABASE_ERROR, message = ex.message)))
        }
    }
}