/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.filtter

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.piappstudio.giftregister.R
import com.piappstudio.pitheme.theme.Dimen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortScreen(){
    Scaffold(topBar = {
        MediumTopAppBar(title = {
            Text( modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.sort__filter),
                style = MaterialTheme.typography.headlineMedium)
        },
            colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.onPrimary),
            actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")

            }
        })

    }) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)

            ) {

                val (listView, button) = createRefs()
                Column(
                    modifier = Modifier
                        .constrainAs(listView) {
                            top.linkTo(parent.bottom)
                            bottom.linkTo(button.top)


                        }
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Cards(
                        text = stringResource(R.string.gift_type),
                        imageVector = Icons.Default.NavigateNext
                    )
                    Divider()
                    Cards(
                        text = stringResource(R.string.sort_by_name),
                        imageVector = Icons.Default.NavigateNext
                    )
                    Divider()
                    Cards(
                        text = stringResource(R.string.sort_by_amount),
                        imageVector = Icons.Default.NavigateNext
                    )
                    Divider()
                    Cards(
                        text = stringResource(R.string.group_by),
                        imageVector = Icons.Default.NavigateNext
                    )


                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(button){
                        bottom.linkTo(parent.bottom)


                    }) {
                    Text(text = stringResource(R.string.view__result))
                }

            }

        }

    }



}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cards(text:String,imageVector:ImageVector)
{
    Card(modifier = Modifier
        .fillMaxWidth()
    ,shape = RectangleShape)
   {
       Row(modifier = Modifier
           .fillMaxWidth()
           .padding(Dimen.space),
           horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
       ) { Text( text = text, style = MaterialTheme.typography.bodyLarge)
           IconButton(onClick = {}) {
                   Icon(imageVector =imageVector,
                       contentDescription = stringResource(R.string.navigate))

               }

       }


   }

}









@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SortScreen()

}