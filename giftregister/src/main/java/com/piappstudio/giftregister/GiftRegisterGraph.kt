/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.piappstudio.giftregister.ui.event.EventHome
import com.piappstudio.giftregister.ui.event.about.AboutScreen
import com.piappstudio.giftregister.ui.event.contactus.ContactScreen

import com.piappstudio.giftregister.ui.event.filter.FilterOption
import com.piappstudio.giftregister.ui.event.filter.SortScreen
import com.piappstudio.giftregister.ui.event.guestlist.GuestHome
import com.piappstudio.giftregister.ui.event.list.EventEmptyScreen
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pitheme.route.Root
import com.piappstudio.pitheme.route.Route
import com.webcroptech.pihome.updateapp.UpdateScreen


fun NavGraphBuilder.homeGraph() {
    navigation(startDestination = Root.Home.EVENTS, route = Root.HOME) {
        eventGraph()
        giftGraph()
    }

}


@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.eventGraph() {

    navigation(startDestination = Route.Home.EVENT.UPDATE, route = Root.Home.EVENTS) {

        composable(Route.Home.EVENT.LIST) {
            EventHome()
        }
        composable(Route.Home.EVENT.SORTSCREEN) {
           SortScreen(filerOption = FilterOption())
        }
        composable(Route.Home.EVENT.EVENT_EMPTY_SCREEN) {
            EventEmptyScreen()
        }
        composable(Route.Home.EVENT.ABOUT) {
            AboutScreen()
        }
        composable(Route.Home.EVENT.CONTACT_US) {
            ContactScreen()
        }
        composable(Route.Home.EVENT.UPDATE){
            UpdateScreen()
        }



    }
}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.giftGraph() {

    navigation(startDestination = Route.Home.GUEST.LIST, route = Root.Home.GIFTS) {

        composable(Route.Home.GUEST.LIST, arguments = listOf(navArgument(Route.Home.GUEST.Argument.query){type = NavType.StringType})) {
            val strEventInfo = it.arguments?.getString(Route.Home.GUEST.Argument.query)
            val eventInfo = Gson().fromJson(strEventInfo, EventInfo::class.java)
            GuestHome(eventInfo = eventInfo)
        }
    }
}

