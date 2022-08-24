/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.piappstudio.giftregister.ui.event.editevent.EditEventScreen
import com.piappstudio.giftregister.ui.event.editguest.EditGuestScreen
import com.piappstudio.giftregister.ui.event.list.EventListScreen
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun GuestHome() {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =
        BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
                    .background(Color(0XFF0F9D58))) {
                EditGuestScreen{
                    coroutineScope.launch{bottomSheetScaffoldState.bottomSheetState.collapse()}
                }
            }

        },
        sheetPeekHeight = 0.dp
    ) {
        //Content
       GuestLIstScreen{
           coroutineScope.launch{bottomSheetScaffoldState.bottomSheetState.expand()
           }
       }

    }
}