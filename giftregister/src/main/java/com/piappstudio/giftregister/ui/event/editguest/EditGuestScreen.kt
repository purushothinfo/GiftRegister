/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editguest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.piappstudio.giftregister.R
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGuestScreen() {
    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = stringResource(R.string.title_edit_guest))

        })

    }) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(start = Dimen.double_space, end = Dimen.double_space)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(Dimen.double_space)
                ) {
                    Spacer(modifier = Modifier.height(Dimen.triple_space))
                    Text(
                        text = stringResource(R.string.full_name),
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    OutlinedTextField(
                        value = "", onValueChange = {

                        },
                        placeholder = {
                            Text(text = stringResource(R.string.type_name))
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Person"
                            )
                        }, modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    Text(
                        text = stringResource(R.string._address),
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    OutlinedTextField(
                        value = "", onValueChange = {

                        },
                        placeholder = {
                            Text(text = stringResource(R.string.type_address))
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Address"
                            )
                        }, modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.height(Dimen.double_space))

                    RadioButtonDemo()

                    Spacer(modifier = Modifier.height(Dimen.triple_space))

                    Button(onClick = {
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.submit),
                            modifier = Modifier.padding(Dimen.space),
                            fontWeight = FontWeight.Bold
                        )
                    }


                }


            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonDemo() {
    Column {
        val selectedGiftType = remember { mutableStateOf("") }
        Text("Select GiftType")
        Spacer(modifier = Modifier.size(Dimen.double_space))
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
            ){
            RadioButton(selected = selectedGiftType.value =="", onClick = {
                selectedGiftType.value = ""
            })
            Text(GiftType.Cash)
            RadioButton(selected = selectedGiftType.value == GiftType.Gold, onClick = {
                selectedGiftType.value = GiftType.Gold
            },colors = RadioButtonDefaults.colors(Color.Red))
            Text(GiftType.Gold)

            RadioButton(selected = selectedGiftType.value == GiftType.Others, onClick = {
                selectedGiftType.value = GiftType.Others
            },colors = RadioButtonDefaults.colors(Color.Red))
            Text(GiftType.Others)

        }

    }
}

object GiftType {
    const val Cash = "Cash"
    const val Gold= "Gold"
    const val Others="Others"
}

