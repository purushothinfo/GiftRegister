/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.eventinfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventInfo() {

    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = "Event Screen")
        })
    }) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = "Engagement function",
                    Modifier.padding(start = Dimen.double_space, end = Dimen.double_space)
                    , fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "jan 21st,2022",
                    Modifier.padding(start = Dimen.double_space, end = Dimen.double_space),
                    fontWeight = FontWeight.Medium, style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally


                    ){
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = " persons Icon",
                            Modifier
                                .size(Dimen.triple_space)
                                .padding(start = Dimen.double_space, end = Dimen.double_space),

                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "cash amount",
                            Modifier
                                .size(Dimen.triple_space)
                                .padding(start = Dimen.double_space, end = Dimen.double_space)
                        )
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "gold",
                            Modifier
                                .size(Dimen.triple_space)
                                .padding(start = Dimen.double_space, end = Dimen.double_space)
                        )
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "others gift",
                            Modifier
                                .size(Dimen.triple_space)
                                .padding(start = Dimen.double_space, end = Dimen.double_space)
                        )

                    }


                }

            }
        }
    }
}