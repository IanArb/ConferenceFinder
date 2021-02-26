package com.ianarbuckle.conferencesfinder.ui.conferences.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme

@Composable
fun ErrorContent() {
    Row(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_perm_scan_wifi_24px),
                modifier = Modifier.size(250.dp, 250.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                contentDescription = "Wifi"
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