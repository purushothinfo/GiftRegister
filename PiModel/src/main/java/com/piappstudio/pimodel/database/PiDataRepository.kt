/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.database

import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.database.dao.IEventDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiDataRepository @Inject constructor(private val eventDao: IEventDao) {

    suspend fun insert(eventInfo: EventInfo): Flow<Resource<Long?>> {
        val eventId =  makeSafeApiCall {
            eventDao.insert(eventInfo)
        }
    }

    private suspend fun <T> makeSafeApiCall(api: suspend () -> Resource<T?>) = flow {
        try {
            emit(Resource.loading())
            val response = api.invoke()
            emit(Resource.success(response.data))
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }


}