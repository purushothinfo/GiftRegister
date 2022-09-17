/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.Constant.EMPTY_STRING
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pitheme.theme.Dimen

// MVVM = Model- View- ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(lstEvents: List<EventInfo>,
                    onClickFloatingAction: () -> Unit, onClickEventItem:((eventInfo:EventInfo?)->Unit)? = null) {

    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = stringResource(R.string.title_events))
        })
    }) {


        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = Dimen.double_space,
                        end = Dimen.double_space,
                        bottom = Dimen.double_space
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                items(lstEvents) { event->
                    RenderEventView(model = event) {
                        onClickEventItem?.invoke(event)
                    }
                }

            }

            if (lstEvents.isEmpty()) {
                EventEmptyScreen()
            }


            ExtendedFloatingActionButton(
                onClick = { onClickFloatingAction.invoke() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimen.double_space)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.acc_add_new_event)
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderEventView(model: EventInfo, callBack: (() -> Unit)? = null) {

    Card(modifier = Modifier.fillMaxWidth().clickable {
        callBack?.invoke()
    }) {
        Column(modifier = Modifier.padding(Dimen.double_space)) {
            Text(text = model.title ?: EMPTY_STRING,
                style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(Dimen.space))
            Text(text = model.date ?: EMPTY_STRING, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(Dimen.double_space))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemCountView(
                    imageVector = Icons.Default.People,
                    text = model.noOfPeople?.toString()
                )
                ItemCountView(
                    imageVector = Icons.Default.Payments,
                    text = model.cashAmount?.toString()
                )
                ItemCountView(
                    imageVector = Icons.Default.Diamond,
                    text = model.totalGold?.toString()
                )
                ItemCountView(
                    imageVector = Icons.Default.Redeem,
                    text = model.totalOthers?.toString()
                )
            }
        }
    }

}


@Composable
fun ItemCountView(imageVector: ImageVector, text: String?) {

    //text?.let {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = imageVector, contentDescription = text)
            Text(
                text = text?: "N/A",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light
            )
        }
    //}

}

@Composable
fun RenderEmptyScreen() {


}