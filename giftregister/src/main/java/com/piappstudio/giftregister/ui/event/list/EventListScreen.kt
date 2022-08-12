/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.pigiftmodel.Constant.EMPTY_STRING
import com.piappstudio.pigiftmodel.EventInfo
import com.piappstudio.pitheme.theme.Dimen

// MVVM = Model- View- ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(viewModel: EventListScreenViewModel = hiltViewModel()) {

    val lstEvents by viewModel.lstEvents.collectAsState()

    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = stringResource(R.string.title_events))
        })
    }) {

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(start = Dimen.double_space, end = Dimen.double_space)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(lstEvents) { event->
                    // Rendering the row
                    RenderEventView(model = event)

                }
            }
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomEnd).padding(Dimen.double_space)) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.acc_add_new_event))
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderEventView(model:EventInfo) {

    Card(modifier = Modifier.fillMaxWidth()) {
        Column (modifier = Modifier.padding(Dimen.double_space)) {
            Text(text = model.title?:EMPTY_STRING, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Dimen.space))
            Text(text = model.date?: EMPTY_STRING, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(Dimen.double_space))
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
               ItemCountView(imageVector = Icons.Default.People, text = model.noOfPeople?.toString())
               ItemCountView(imageVector = Icons.Default.Payments, text = model.cashAmount?.toString())
               ItemCountView(imageVector = Icons.Default.Diamond, text = model.totalGold?.toString())
               ItemCountView(imageVector = Icons.Default.Redeem, text = model.totalOthers?.toString())
            }
        }
    }

}


@Composable
fun ItemCountView(imageVector: ImageVector, text:String?) {
    text?.let {
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = imageVector, contentDescription = text)
            Text(text = text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun RenderEmptyScreen() {

    EventEmptyScreen()


}