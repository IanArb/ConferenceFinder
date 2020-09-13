package com.ianarbuckle.conferencesfinder.ui.conferences.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme

@Composable
fun ErrorContent() {
    Row(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalGravity = Alignment.CenterVertically) {
        Column(
            horizontalGravity = Alignment.CenterHorizontally
        ){
            Image(
                imageResource(id = R.drawable.ic_perm_scan_wifi_24px),
                modifier = Modifier.preferredWidthIn(250.dp, 250.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Oops! Something went wrong.",
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {

            }) {
                Text(
                    text = "Try Again"
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ConferencesTheme {
        ErrorContent()
    }
}