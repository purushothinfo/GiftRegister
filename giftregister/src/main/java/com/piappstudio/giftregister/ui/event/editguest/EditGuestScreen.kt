/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.editguest

import android.view.View
import android.widget.RadioButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
    val radioButton= listOf("cash,gold,others")
    val selectedButton= remember {
        mutableStateOf(radioButton.first())
    }
    radioButton.forEach {
        val isSelected = it==selectedButton.value
        val colors =RadioButtonDefaults.colors(
            selectedColor = colorResource(id = androidx.appcompat.R.color.abc_background_cache_hint_selector_material_light)
        )
    }
}