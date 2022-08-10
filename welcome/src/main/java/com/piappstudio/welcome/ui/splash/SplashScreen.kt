package com.piappstudio.welcome.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.piappstudio.pitheme.theme.AppTheme
import com.piappstudio.pitheme.theme.Dimen
import com.piappstudio.welcome.R
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.wrapContentSize(align = Alignment.Center).align(
                    Alignment.Center)
            ) {


                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.gift))
                val progress by animateLottieCompositionAsState(composition)
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.fillMaxSize(.75f),
                    progress = progress
                )
                LaunchedEffect(key1 = progress) {
                    Timber.d("Progress : $progress")
                    if (progress == 1.0f) {
                        Timber.d("Animation is completed")
                    }
                }
            }

        }
    }
}