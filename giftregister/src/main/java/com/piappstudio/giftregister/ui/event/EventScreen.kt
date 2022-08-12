package com.piappstudio.giftregister.ui.event
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piappstudio.pitheme.theme.Dimen
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen() {
    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = "Engagement function")
        })
    }){it
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
            horizontalAlignment = Alignment.CenterHorizontally,){
            item {
                Row( modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Person,
                        contentDescription =" persons Icon",
                        Modifier
                            .size(Dimen.triple_space)
                            .padding(Dimen.double_space)
                    )
                    Icon(imageVector = Icons.Default.Star,
                        contentDescription = "cash amount",
                        Modifier
                            .size(Dimen.triple_space)
                            .padding(Dimen.double_space)
                    )
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = "gold",
                        Modifier
                            .size(Dimen.triple_space)
                            .padding(Dimen.double_space)
                    )
                    Icon(imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "others gift",
                        Modifier
                            .size(Dimen.triple_space)
                            .padding(Dimen.double_space)
                    )


                }

            }
        }
    }
}