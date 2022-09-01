/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pitheme.route

/** Class contains all route information*/
object Route {
    object Welcome {
        val SPLASH = "splash"
        val FEATURE = "feature"
        val TNC = "termscondition"
    }

    object Home {
        object EVENT {
            val LIST = "events"
            val EVENTEMPTSCREEN ="eventemptyscreen"
            val GUESTLIST ="guestlist"
            val EDITGUEST ="editguest"
            val EDITEVENT ="editevent"
        }

        object GIFT {
            val LIST = "gifts"
            val MANAGE_GIFT = "gift"
        }

    }

    object Auth {
        val REGISTER = "register"

        object Login {
            val LOGIN = "login"
            val FORGETPASSWORD = "forgetpassword"
        }
    }

}

/** To hold the nav graph roots */
object Root {
    val APPROOT = "approot"
    val WELCOME = "welcomeroot"
    val HOME = "homeroot"
    val AUTH = "authroot"

    object Home {
        val EVENTS = "eventsroot"
        val GIFTS = "giftroot"
    }

    object Auth {
        val LOGIN = "loginroot"
    }
}