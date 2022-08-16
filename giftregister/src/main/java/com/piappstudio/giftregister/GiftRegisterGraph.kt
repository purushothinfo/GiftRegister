/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.piappstudio.giftregister.ui.event.editguest.EditGuestScreen
import com.piappstudio.giftregister.ui.event.guestlist.GuestLIstScreen
import com.piappstudio.giftregister.ui.event.list.EventEmptyScreen
import com.piappstudio.giftregister.ui.event.list.EventListScreen
import com.piappstudio.giftregister.ui.gift.GiftDetailScreen
import com.piappstudio.giftregister.ui.gift.GiftListScreen
import com.piappstudio.pitheme.route.Root
import com.piappstudio.pitheme.route.Route


fun NavGraphBuilder.homeGraph() {
    navigation(startDestination = Root.Home.EVENTS, route = Root.HOME) {
        eventGraph()
        giftGraph()
    }

}


fun NavGraphBuilder.eventGraph() {

    navigation(startDestination = Route.Home.EVENT.EDITGUEST, route = Root.Home.EVENTS) {

        composable(Route.Home.EVENT.LIST) {
            EventListScreen()
        }
        composable(Route.Home.EVENT.EVENTEMPTSCREEN) {
            EventEmptyScreen()
        }
        composable(Route.Home.EVENT.GUESTLIST) {
           GuestLIstScreen()
        }
        composable(Route.Home.EVENT.EDITGUEST) {
            EditGuestScreen()
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

