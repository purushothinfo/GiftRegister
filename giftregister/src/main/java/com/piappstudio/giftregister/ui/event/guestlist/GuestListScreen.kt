/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.compose.foundation.clickable
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
import com.piappstudio.giftregister.ui.event.list.ItemCountView
import com.piappstudio.pimodel.Constant
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuestLIstScreen(viewModel: GuestLIstViewModel = hiltViewModel(),callback: ()->Unit) {

    val lstGuest by viewModel.lstGuest.collectAsState()

    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = stringResource(R.string.guestlist))
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
                items(lstGuest) { guest ->
                    // Rendering the row
                    RenderGuestListView(guestInfo = guest, viewModel)

                }
            }
            ExtendedFloatingActionButton(
                onClick = {callback.invoke() },
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
fun RenderGuestListView(guestInfo: GuestInfo, viewModel: GuestLIstViewModel) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { viewModel.onClickEdit(guestInfo) }) {
        Column(modifier = Modifier.padding(Dimen.double_space)) {
            Text(
                text = guestInfo.name ?: Constant.EMPTY_STRING,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(Dimen.space))
            Text(
                text = guestInfo.address ?: Constant.EMPTY_STRING,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(Dimen.double_space))
            Text(
                text = guestInfo.phone ?: Constant.EMPTY_STRING,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(Dimen.double_space))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemCountView(
                    imageVector = Icons.Default.Payments,
                    text = guestInfo.giftType.toString()
                )
                ItemCountView(
                    imageVector = Icons.Default.Redeem,
                    text = guestInfo.giftValue.toString()
                )
                ItemCountView(
                    imageVector = Icons.Default.Diamond,
                    text = guestInfo.giftType.toString()
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

