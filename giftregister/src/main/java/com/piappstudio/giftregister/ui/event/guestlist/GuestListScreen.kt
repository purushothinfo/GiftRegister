/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.giftregister.ui.event.list.EventEmptyScreen
import com.piappstudio.giftregister.ui.event.list.ItemCountView
import com.piappstudio.giftregister.ui.theme.Cash
import com.piappstudio.giftregister.ui.theme.Diamond
import com.piappstudio.giftregister.ui.theme.Gift
import com.piappstudio.giftregister.ui.theme.People
import com.piappstudio.pimodel.Constant
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pimodel.GiftType
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pinavigation.NavInfo
import com.piappstudio.pitheme.component.getColor
import com.piappstudio.pitheme.component.piShadow
import com.piappstudio.pitheme.component.piTopBar
import com.piappstudio.pitheme.route.Route
import com.piappstudio.pitheme.theme.Dimen

fun giftImage(guestInfo: GuestInfo):ImageVector {
    return when (guestInfo.giftType)  {
        GiftType.GOLD -> {
            Icons.Default.Diamond
        }
        GiftType.CASH -> {
            Icons.Default.Payments
        } else -> {
            Icons.Default.Redeem
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GuestListScreen(
    eventInfo: EventInfo?,
    viewModel: GuestListViewModel = hiltViewModel(),
    callback: () -> Unit,
    onClickGuestItem: (guestInfo: GuestInfo) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchGuest()
    }

    val lstGuest by viewModel.lstGuest.collectAsState()

    Scaffold {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .piTopBar()
                    .padding(top = Dimen.space, bottom = Dimen.space), ) {
                    IconButton(onClick = { viewModel.navManager.navigate(NavInfo(Route.Control.Back)) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back
                            )
                        )
                    }
                    Column (modifier = Modifier.padding(start = Dimen.space)){
                        Text(
                            text = eventInfo?.title ?: stringResource(R.string.guestlist),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = eventInfo?.date ?: Constant.EMPTY_STRING,
                            style = MaterialTheme.typography.titleSmall
                        )

                    }
                }

                Spacer(modifier = Modifier.height(Dimen.double_space))
                if (lstGuest.isEmpty()) {
                    EventEmptyScreen()
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .padding(start = Dimen.space, end = Dimen.space)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    stickyHeader {
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Dimen.space)) {

                            ItemCountView(
                                modifier = Modifier.weight(0.2f, true),
                                imageVector = Icons.Default.People,
                                text = lstGuest.size.toString() ?: "0",
                                color = People
                            )
                            ItemCountView(
                                modifier = Modifier.weight(0.2f, true),
                                imageVector = Icons.Default.Diamond,
                                text = lstGuest.filter { it.giftType == GiftType.GOLD }
                                    .sumOf { it.giftValue?.toDouble() ?: 0.0 }.toString() ?: "0",
                                color = Diamond
                            )
                            ItemCountView(
                                modifier = Modifier.weight(0.2f, true),
                                imageVector = Icons.Default.Redeem,
                                text = lstGuest.filter { it.giftType == GiftType.OTHERS }.size.toString()
                                    ?: "0",
                                color = Gift
                            )
                            ItemCountView(
                                modifier = Modifier.weight(.4f, true),
                                imageVector = Icons.Default.Payments,
                                text = lstGuest.filter { it.giftType == GiftType.CASH }
                                    .sumOf { it.giftValue?.toDouble() ?: 0.0 }.toString() ?: "0",
                                color = Cash
                            )
                        }
                    }
                    items(lstGuest) { guest ->
                        // Rendering the row
                        RenderGuestListView(guestInfo = guest, viewModel, onClickGuestItem)

                    }
                }
            }


            ExtendedFloatingActionButton(
                onClick = { callback.invoke() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimen.double_space)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Guest list"
                )

            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderGuestListView(
    guestInfo: GuestInfo,
    viewModel: GuestListViewModel,
    onClickGuestItem: (guestInfo: GuestInfo) -> Unit
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = Dimen.space, end = Dimen.space)
        .clickable { onClickGuestItem.invoke(guestInfo) }) {
        Row (modifier = Modifier
            .padding(start = Dimen.double_space, end = Dimen.double_space)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Column {
                Text(
                    text = guestInfo.name ?: Constant.EMPTY_STRING,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black
                )
                guestInfo.address?.let {
                    Spacer(modifier = Modifier.height(Dimen.space))
                    Text(
                        text = guestInfo.address ?: Constant.EMPTY_STRING,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                guestInfo.phone?.let {
                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    Text(
                        text = guestInfo.phone ?: Constant.EMPTY_STRING,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }


            val image = giftImage(guestInfo)
            Column(
                modifier = Modifier.padding(top= Dimen.double_space, bottom = Dimen.double_space),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = image, contentDescription = guestInfo.giftValue, tint = guestInfo.getColor())
                Text(
                    text = guestInfo.displayGiftValue()?: "N/A",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }

        }
    }
}

@Composable
fun ItemView(imageVector: ImageVector, text: String?) {
    text?.let {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = imageVector, contentDescription = text)
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Black
            )
        }
    }

}

