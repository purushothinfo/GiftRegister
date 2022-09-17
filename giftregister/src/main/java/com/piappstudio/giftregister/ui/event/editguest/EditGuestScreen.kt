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
import androidx.compose.material.icons.filled.Close
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.Constant.EMPTY_STRING
import com.piappstudio.pimodel.GiftType
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pitheme.component.PiErrorView
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGuestScreen(
    viewModel: EditGuestViewModel = hiltViewModel(),
    callback: () -> Unit
) {

    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = stringResource(R.string.title_edit_guest))

        }, actions = {
            IconButton(onClick = { callback.invoke() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")

            }
        })

    }) {

        // READ
        val guestInfo by viewModel.guestInfo.collectAsState()

        val errorInfo by viewModel.errorInfo.collectAsState()

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
                        text = stringResource(R.string.name),
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    OutlinedTextField(
                        value = guestInfo.name ?: EMPTY_STRING, onValueChange = { name ->
                            viewModel.updateName(name)

                        }, isError = errorInfo.nameError.isError,
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
                    PiErrorView(uiError = errorInfo.nameError)

                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    Text(
                        text = stringResource(R.string._address),
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    OutlinedTextField(
                        value = guestInfo.address ?: EMPTY_STRING, onValueChange = { address ->
                            viewModel.updateAddress(address)
                        }, isError = errorInfo.addressError.isError,
                        placeholder = {
                            Text(text = stringResource(R.string.type_address))
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = stringResource(R.string.icon_address)
                            )
                        }, modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    PiErrorView(uiError = errorInfo.addressError)
                    Spacer(modifier = Modifier.height(Dimen.double_space))

                    GiftTypeOption(guestInfo.giftType) { giftType ->
                        viewModel.updateGiftType(giftType)
                    }

                    Spacer(modifier = Modifier.height(Dimen.fourth_space))

                    Button(onClick = {
                        viewModel.onClickSubmit()
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
fun GiftTypeOption(type: GiftType, callback: (selectedType: GiftType) -> Unit) {
    Column {
        Text(stringResource(R.string.select_gift_type))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = type == GiftType.CASH, onClick = {
                callback.invoke(GiftType.CASH)

            }, colors = RadioButtonDefaults.colors(Color.Green))
            Text(stringResource(R.string.cash))
            RadioButton(selected = type == GiftType.GOLD, onClick = {
                callback.invoke(GiftType.GOLD)
            }, colors = RadioButtonDefaults.colors(Color.Green))
            Text(stringResource(R.string.gold))

            RadioButton(selected = type == GiftType.OTHERS, onClick = {
                callback.invoke(GiftType.OTHERS)
            }, colors = RadioButtonDefaults.colors(Color.Green))
            Text(stringResource(R.string.others))

        }

    }
}

