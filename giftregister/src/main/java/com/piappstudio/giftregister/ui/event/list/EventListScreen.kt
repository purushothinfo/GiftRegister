/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.piappstudio.giftregister.R
import com.piappstudio.giftregister.ui.theme.Cash
import com.piappstudio.giftregister.ui.theme.Diamond
import com.piappstudio.giftregister.ui.theme.Gift
import com.piappstudio.giftregister.ui.theme.People
import com.piappstudio.pimodel.Constant.EMPTY_STRING
import com.piappstudio.pimodel.EventInfo
import com.piappstudio.pitheme.component.piImageCircle
import com.piappstudio.pitheme.component.piShadow
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


        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
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

    Card(modifier = Modifier
        .fillMaxWidth()
        .piShadow()
        .clickable {
            callBack?.invoke()
        }) {
        Column(modifier = Modifier.padding(Dimen.double_space)) {
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column (modifier = Modifier.weight(0.6f, true)){
                    Text(text = model.title ?: EMPTY_STRING,
                        style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                    Spacer(modifier = Modifier.height(Dimen.space))
                    Text(text = model.date ?: EMPTY_STRING, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Medium)
                }
                ItemCountView(
                    modifier = Modifier
                        .weight(0.4f, true)
                        .padding(start = Dimen.double_space),
                    imageVector = Icons.Default.Payments,
                    text = model.cashAmount?.toString(),
                    color = Cash,
                    isShadowEnabled = false
                )
            }

           Spacer(modifier = Modifier.height(Dimen.double_space))
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimen.half_space),
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemCountView(
                    modifier = Modifier.weight(1f, true),
                    imageVector = Icons.Default.People,
                    text = model.noOfPeople?.toString(),
                    color = People
                )
                ItemCountView(
                    modifier = Modifier.weight(1f, true),
                    imageVector = Icons.Default.Diamond,
                    text = model.totalGold?.toString(),
                    color = Diamond
                )
                ItemCountView(
                    modifier = Modifier.weight(1f, true),
                    imageVector = Icons.Default.Redeem,
                    text = model.totalOthers?.toString(),
                    color = Gift
                )
            }
        }
    }

}


@Composable
fun ItemCountView(imageVector: ImageVector, text: String?, modifier: Modifier = Modifier, color:Color, isShadowEnabled:Boolean = true) {

    var updatedModifier =  modifier.fillMaxSize()

    if (isShadowEnabled) {
        updatedModifier = updatedModifier.piShadow(Dimen.half_space)
    }
    //text?.let {
        Column(
            modifier =updatedModifier.padding(Dimen.space),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = imageVector, contentDescription = text, tint = color, modifier = Modifier.padding(top = Dimen.half_space))
            Text(
                text = text?: "$100",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    //}

}

@Composable
fun RenderEmptyScreen() {


}