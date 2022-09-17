/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pitheme.route

/** Class contains all route information*/
object Route {
    object Welcome {
        const val SPLASH = "splash"
        const val FEATURE = "feature"
        const val TNC = "termscondition"
    }

    object Home {
        object EVENT {
            const val LIST = "events"
            const val EVENT_EMPTY_SCREEN ="eventemptyscreen"
        }

        object GUEST {
            object Argument {
                const val eventId= "eventId"
                const val guestId = "guestId"
            }
            const val LIST = "guest/{${Argument.eventId}}"
            const val MANAGE_GIFT = "guest"
            fun guestList(eventId:Long?):String {
                return "guest/${eventId?:0}"
            }
        }

    }

    object Auth {
        const val REGISTER = "register"

        object Login {
            const val LOGIN = "login"
            const val FORGET_PASSWORD = "forgetpassword"
        }
    }

}

/** To hold the nav graph roots */
object Root {
    const val APPROOT = "approot"
    const val WELCOME = "welcomeroot"
    const val HOME = "homeroot"
    const val AUTH = "authroot"

    object Home {
        const val EVENTS = "eventsroot"
        const val GIFTS = "giftroot"
    }

    object Auth {
        const val LOGIN = "loginroot"
    }
}