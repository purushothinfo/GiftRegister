/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.picloud.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.piappstudio.pimodel.Constant
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.pref.PiPrefKey
import com.piappstudio.pimodel.pref.PiPreference
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

@HiltWorker
class GoogleDriveSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface WorkerProviderEntryPoint {
        fun piDriveManager(): PiDriveManager
        fun piPreference(): PiPreference
    }

    override suspend fun doWork(): Result {
        val entryPoint =
            EntryPointAccessors.fromApplication(context, WorkerProviderEntryPoint::class.java)
        val piDriveManager = entryPoint.piDriveManager()
        val piPreference = entryPoint.piPreference()
        piDriveManager.doSync().collect {
            it.data?.let { progress ->
                setForeground(createForegroundInfo(progress))
            }
            if (it.status == Resource.Status.SUCCESS) {
                val date = Constant.PiFormat.orderItemDisplay.format(Date())
                piPreference.save(PiPrefKey.LAST_SYNC_TIME, date)
                setForeground(createForegroundInfo("Last sync date: $date"))
            }
        }
        return Result.success()
    }
}