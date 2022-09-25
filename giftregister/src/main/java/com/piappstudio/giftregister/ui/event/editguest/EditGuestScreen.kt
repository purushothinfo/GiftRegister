/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editguest

import android.icu.util.Currency
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.piappstudio.giftregister.R
import com.piappstudio.giftregister.ui.event.guestlist.giftImage
import com.piappstudio.pimodel.Constant.EMPTY_STRING
import com.piappstudio.pimodel.GiftType
import com.piappstudio.pimodel.Resource
import com.piappstudio.pitheme.component.*
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGuestScreen(
    viewModel: EditGuestViewModel,
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

        val piFocus = rememberPiFocus()

        // READ
        val guestInfo by viewModel.guestInfo.collectAsState()
        val errorInfo by viewModel.errorInfo.collectAsState()

        if (errorInfo.progress.status == Resource.Status.LOADING) {
            PiProgressIndicator()
        } else if (errorInfo.progress.status == Resource.Status.SUCCESS) {
            callback.invoke()
        }

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(start = Dimen.double_space, end = Dimen.double_space)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimen.double_space),
        ) {
            item {
                Column {
                    Spacer(modifier = Modifier.height(Dimen.double_space))
                    FieldTitle(title = stringResource(R.string.name))

                    Spacer(modifier = Modifier.height(Dimen.space))
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
                            .fillMaxWidth()
                            .piFocus(errorInfo.nameError.focusRequester, piFocus),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    PiErrorView(uiError = errorInfo.nameError)

                    Spacer(modifier = Modifier.height(Dimen.double_space))


                    FieldTitle(title = stringResource(R.string._address))
                    Spacer(modifier = Modifier.height(Dimen.space))
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
                            .fillMaxWidth()
                            .piFocus(errorInfo.addressError.focusRequester, piFocus),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    PiErrorView(uiError = errorInfo.addressError)
                    Spacer(modifier = Modifier.height(Dimen.double_space))

                    GiftTypeOption(guestInfo.giftType) { giftType ->
                        viewModel.updateGiftType(giftType)
                    }

                    val keyboardType = when (guestInfo.giftType) {
                        GiftType.GOLD, GiftType.CASH -> {
                            KeyboardType.Number
                        }
                        else -> {
                            KeyboardType.Text
                        }
                    }

                    val description = when (guestInfo.giftType) {
                        GiftType.GOLD -> {
                            stringResource(id = R.string.gold_in_gram)
                        }
                        GiftType.CASH -> {
                            stringResource(id = R.string.cash)
                        }
                        else -> {
                            stringResource(id = R.string.gift_detail)
                        }
                    }
                    FieldTitle(title = description)
                    Spacer(modifier = Modifier.height(Dimen.space))

                    OutlinedTextField(
                        value = guestInfo.giftValue?: EMPTY_STRING, onValueChange = { quantity ->
                            viewModel.updateQuantity(quantity)
                        }, isError = errorInfo.quantity.isError,
                        placeholder = {
                            Text(text = description)
                        },
                        leadingIcon = {
                            Icon(
                                giftImage(guestInfo),
                                contentDescription = stringResource(R.string.icon_address)
                            )
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .piFocus(errorInfo.quantity.focusRequester, piFocus),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next, keyboardType =
                            keyboardType
                        )
                    )
                    PiErrorView(uiError = errorInfo.quantity)

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

@Composable
fun FieldTitle(title:String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.Medium
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftTypeOption(type: GiftType, callback: (selectedType: GiftType) -> Unit) {
    Column {
        FieldTitle(title = stringResource(R.string.select_gift_type))
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

