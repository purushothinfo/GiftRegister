/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.contactus

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piappstudio.giftregister.R
import com.piappstudio.pitheme.theme.Dimen

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 60.dp),

                        text = stringResource(id =R.string.contact_us),
                        fontWeight =FontWeight.ExtraBold,
                        style = MaterialTheme.typography.headlineSmall,



                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.onPrimary,
                elevation = 10.dp
            )
        }
    ){
        Column(


        modifier = Modifier
            .padding(it)
            .padding(Dimen.double_space)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ){
        Text(
            text = stringResource(R.string.email_id),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(Dimen.space)
                .fillMaxWidth()


        )
            Text(
                text = stringResource(R.string.piappstudio_gmail), fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = Dimen.space)
                    .fillMaxWidth())
            Spacer(modifier = Modifier.height(Dimen.triple_space))

            Text(
                text ="Our Apps",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(Dimen.space)
                    .fillMaxWidth())


            CardDetails(text = stringResource(R.string.digital_diary),
                painter= painterResource(id =R.drawable.diary))

            CardDetails(text = "Fresh Look",
                painter= painterResource(id =R.drawable.freshlook))
            
            Spacer(modifier = Modifier.height(Dimen.fourth_space))

            Text(
                text ="Social Media",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(Dimen.space)
                    .fillMaxWidth())
            Spacer(modifier = Modifier.height(Dimen.double_space))


            Row(modifier = Modifier
               .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically) {

               SocialMediaCard(painter =painterResource(id = R.drawable.youtube))
               SocialMediaCard(painter =painterResource(id = R.drawable.twitter))
               SocialMediaCard(painter =painterResource(id = R.drawable.linkedin))
               SocialMediaCard(painter =painterResource(id = R.drawable.facebook))




           }
            Spacer(modifier = Modifier.height(Dimen.height))
            Spacer(modifier = Modifier.height(Dimen.height))
            Spacer(modifier = Modifier.height(Dimen.fifth_space))
            Text(
                text ="App Version: 1.0.0(1.0)",
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start=0.dp))
    }



    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetails(painter:Painter, text: String)

{
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(Dimen.space)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { }) {
                Box(modifier = Modifier
                    .padding(Dimen.space)) {
                    Image(
                        painter = painter,
                        contentDescription = "Image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(35.dp))
                            .size(100.dp)
                            .padding(Dimen.space),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight =FontWeight.SemiBold
            )
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.NavigateNext,
                    contentDescription = "Localized description",
                )

            }


        }

    }


}

@Composable
fun SocialMediaCard(painter:Painter) {

        IconButton(onClick = { })
        {
            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier
                    .size(55.dp)
                    .padding(Dimen.space),
                contentScale = ContentScale.Crop
            )


        }
  }