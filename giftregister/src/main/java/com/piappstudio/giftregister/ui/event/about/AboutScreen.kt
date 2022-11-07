/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.pinavigation.NavInfo
import com.piappstudio.pitheme.route.Root
import com.piappstudio.pitheme.theme.Dimen
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(viewModel: AboutViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        SmallTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.about_screen),
                    modifier = Modifier.padding(start = Dimen.space)
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.onPrimary),
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.arrow_back)
                    )

                }
            })
    }) {
        it
        Column(
            modifier = Modifier
                .padding(top = Dimen.height)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CardDetails(
                text = stringResource(id = R.string.frequently_asked_questions),
                imageVector = Icons.Default.NavigateNext, onClick = {
                    viewModel.navManager.navigate(routeInfo = NavInfo(id = Root.DRIVE))
                }

            )
           Divider(color=MaterialTheme.colorScheme.onPrimary)
            CardDetails(
                text = stringResource(R.string.learn_More),

                imageVector = Icons.Default.NavigateNext, onClick = {}
            )
          Divider(color=MaterialTheme.colorScheme.onPrimary)

            CardDetails(
                text = stringResource(R.string.call_customer_service),
                imageVector = Icons.Default.NavigateNext, onClick = {}
            )
            Divider(color=MaterialTheme.colorScheme.onPrimary)


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetails(
    text: String,
    imageVector: ImageVector,
    onClick:()->Unit
) {


    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,

        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.double_space),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = Dimen.double_space),
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = {onClick.invoke() }) {
                Icon(
                    imageVector = imageVector,
                    modifier = Modifier
                        .padding(start = Dimen.space),
                    contentDescription = text
                )

            }
        }
    }

}