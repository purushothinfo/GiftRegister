/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.piappstudio.giftregister.ui.event.EventDetailScreen
import com.piappstudio.giftregister.ui.event.EventListScreen
import com.piappstudio.giftregister.ui.event.EventScreen
import com.piappstudio.giftregister.ui.gift.GiftDetailScreen
import com.piappstudio.giftregister.ui.gift.GiftListScreen
import com.piappstudio.pitheme.route.Root
import com.piappstudio.pitheme.route.Route


fun NavGraphBuilder.homeGraph() {
    eventGraph()
    giftGraph()
}


fun NavGraphBuilder.eventGraph() {

    navigation(startDestination = Route.Home.EVENT.EVENTSCREEN, route = Root.Home.EVENTS) {

        composable(Route.Home.EVENT.LIST) {
            EventListScreen()
        }

        composable(Route.Home.EVENT.MANAGE_EVENT) {
            EventDetailScreen()
        }
        composable(Route.Home.EVENT.EVENTSCREEN) {
            EventScreen()
        }

    }
}

fun NavGraphBuilder.giftGraph() {

    navigation(startDestination = Route.Home.GIFT.LIST, route = Root.Home.GIFTS) {

        composable(Route.Home.GIFT.LIST) {
            GiftListScreen()
        }
        composable(Route.Home.GIFT.LIST) {
            GiftListScreen()
        }

        composable(Route.Home.GIFT.MANAGE_GIFT) {
            GiftDetailScreen()
        }
    }
}

