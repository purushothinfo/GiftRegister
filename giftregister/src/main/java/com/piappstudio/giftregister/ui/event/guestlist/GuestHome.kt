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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.giftregister.ui.event.editguest.EditGuestScreen
import com.piappstudio.giftregister.ui.event.editguest.EditGuestViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@ExperimentalMaterialApi
@Composable
fun GuestHome(
    eventId: Long? = 0,
    guestListViewModel: GuestListViewModel = hiltViewModel(),
    editGuestViewModel: EditGuestViewModel = hiltViewModel()
) {

    Timber.d("EventId: $eventId")
    LaunchedEffect(key1 = eventId) {
        guestListViewModel.selectedEventId = eventId ?: 0
    }

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
                    .fillMaxHeight(.7f)
                    .background(Color(0XFF0F9D58))
            ) {
                EditGuestScreen(
                    viewModel = editGuestViewModel
                ) {
                    coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                }
            }

        },
        sheetPeekHeight = 0.dp
    ) {
        //Content
        GuestListScreen(viewModel = guestListViewModel, callback = {
            coroutineScope.launch {
                // This is new event
                editGuestViewModel.updateGuestInfo(null)
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
        }) {
            coroutineScope.launch {
                // This is old event
                editGuestViewModel.updateGuestInfo(it)
                bottomSheetScaffoldState.bottomSheetState.expand()
            }

        }

    }
}