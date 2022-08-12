/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist
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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.pigiftmodel.Constant
import com.piappstudio.pigiftmodel.GuestInfo
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuestLIstScreen(viewModel: GuestLIstViewModel= hiltViewModel()){

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
                    RenderGuestListView(model = guest )

                }
            }
            ExtendedFloatingActionButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimen.double_space)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Guest list"
                    )
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderGuestListView(model:GuestInfo) {

    Card(modifier = Modifier.fillMaxWidth()) {
        Column (modifier = Modifier.padding(Dimen.double_space)) {
            Text(text = model.name?: Constant.EMPTY_STRING, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Dimen.space))
            Text(text = model.address?: Constant.EMPTY_STRING, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(Dimen.double_space))
            Text(text = model.phone?: Constant.EMPTY_STRING , style = MaterialTheme.typography.titleSmall)


        }
    }
}
