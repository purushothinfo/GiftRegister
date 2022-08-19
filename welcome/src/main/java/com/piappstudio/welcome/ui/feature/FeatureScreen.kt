package com.piappstudio.welcome.ui.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun FeatureScreen() {
    @Composable
    fun ItemView() {
        Row(modifier = Modifier.padding(start = Dimen.doubleSpace, end = Dimen.doubleSpace)) {
            Text(text = "A")
            Text(text = "B")
            Text(text = "Element C")
        }
    }

    ConstraintLayout (modifier = Modifier.fillMaxSize()) {

        // Declare the constraints
        val (list, addButton) = createRefs()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.doubleSpace),
            modifier = Modifier.fillMaxWidth().constrainAs(list) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(addButton.top)
                height = Dimension.fillToConstraints
            }
        ) {
            stickyHeader {
                Text(
                    text = "Sticky Header",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .padding(Dimen.doubleSpace)
                )
            }
            items(100) {
                ItemView()
            }
        }

        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().constrainAs(addButton) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

                .padding(Dimen.doubleSpace)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new item")
        }
    }
}