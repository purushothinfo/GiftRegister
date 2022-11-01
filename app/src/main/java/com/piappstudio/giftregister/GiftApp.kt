/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.piappstudio.picloud.ui.auth.isUserLoggedIn
import com.piappstudio.picloud.worker.GoogleDriveSyncWorker
import com.piappstudio.picloud.worker.makeStatusNotification
import com.piappstudio.pimodel.PiSession
import com.piappstudio.pimodel.pref.PiPrefKey
import com.piappstudio.pimodel.pref.PiPreference
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class GiftApp : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var preference: PiPreference

    @Inject
    lateinit var piSession: PiSession
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        piSession.appName = applicationContext.getString(R.string.app_name)
        piSession.packageName = BuildConfig.APPLICATION_ID
        scheduleSyncWorker()
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder().setWorkerFactory(workerFactory).build()

    private fun scheduleSyncWorker() {
        val isLoggedIn = preference.isUserLoggedIn()
        if (isLoggedIn) {
            Timber.d("User is already signed in. So schedule the working operation")
            // User is logged in, schedule the background operation
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()
            val work =
                PeriodicWorkRequest.Builder(GoogleDriveSyncWorker::class.java, 20L, TimeUnit.MINUTES).setConstraints(constraints).build()
            val workManager = WorkManager.getInstance(this)

            workManager.enqueueUniquePeriodicWork(
                piSession.appName + "-Worker",
                ExistingPeriodicWorkPolicy.KEEP,
                work
            )
        }
    }
}