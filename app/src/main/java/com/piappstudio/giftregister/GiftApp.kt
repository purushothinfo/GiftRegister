/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister

import android.app.Application
import androidx.compose.ui.res.stringResource
import com.piappstudio.pimodel.PiSession
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class GiftApp : Application() {
    @Inject
    lateinit var piSession: PiSession
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        piSession.appName = applicationContext.getString(R.string.app_name)
        piSession.packageName = BuildConfig.APPLICATION_ID
    }
}