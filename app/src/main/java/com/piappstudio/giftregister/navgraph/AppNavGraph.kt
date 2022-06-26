package com.piappstudio.giftregister.navgraph

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/** To build the navigation graph*/
@Composable
fun AppNavGraph(navController: NavHostController) {
   NavHost(navController = navController , startDestination = "Splash") {

   }
}



@Composable
fun NavGraphBuilder.WelcomeNavGraph (navController: NavHostController) {

}

@Composable
fun NavGraphBuilder.AuthNavGraph(navController: NavController) {

}

@Composable
fun NavGraphBuilder.HomeNavGraph(navController: NavController) {

}