package com.example.quicksearch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.quicksearch.model.Item
import com.example.quicksearch.model.Media
import com.example.quicksearch.R
import com.example.quicksearch.Utils

/**
 * this class is used to display the detail of the item selected
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDetailsScreen(
    onBackClicked: () -> Unit,
    item: Item
) {
    Scaffold(
        modifier = Modifier.background(color = Color.White),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.text_detail_page), fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_vd_arrow_back),
                            contentDescription = stringResource(R.string.text_back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .safeContentPadding()
                .padding(padding)
                .padding(vertical = 15.dp)
        ) {
            LazyColumn {
                item {
                    AsyncImage(
                        model = item.mediaUrl() ?: R.drawable.img,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .height(150.dp)
                            .background(Color.LightGray, RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Spacer(Modifier.height(10.dp))
                }

                item {
                    Text(text = "Title: " + (item.title ?: "Title is Not available"))
                }
                item {
                    Spacer(Modifier.height(10.dp))
                }

                item {
                    Text(text = "Description: " + (item.description?.let { Utils.toHtmlString(it) }
                        ?: "Description is Not available"))
                }
                item {
                    Spacer(Modifier.height(10.dp))
                }
                item {
                    Text(text = "Author: " + (item.author ?: "Author details is Not available"))
                }

                item {
                    Spacer(Modifier.height(10.dp))
                }
                item {
                    Text(text = "Published Date: " + (item.date_taken?.let { Utils.formatDate(it) }
                        ?: "Date is Not available"))
                }

                item {
                    Spacer(Modifier.height(10.dp))
                }
            }

        }
    }
}

@Preview
@Composable
private fun SearchDetailsScreenPreview() {
    SearchDetailsScreen(
        onBackClicked = {},
        item = Item(
            title = "red-tailed hawk (Buteo jamaicensis)",
            link = "https://www.flickr.com/photos/semajl/54069310911/",
            media = Media("https://live.staticflickr.com/65535/54069310911_cd9f4bfcbd_m.jpg"),
            date_taken = "2024-10-02T14:58:09-08:00",
            description = " <p><a href=\"https://www.flickr.com/people/semajl/\">jbernard1968</a> posted a photo:</p> <p><a href=\"https://www.flickr.com/photos/semajl/54069310911/\" title=\"red-tailed hawk (Buteo jamaicensis)\"><img src=\"https://live.staticflickr.com/65535/54069310911_cd9f4bfcbd_m.jpg\" width=\"205\" height=\"240\" alt=\"red-tailed hawk (Buteo jamaicensis)\" /></a></p> <p>Golden eyes begin to visualize a would be prize...</p> ",
            published = "2024-10-15T17:14:54Z",
            author = "nobody@flickr.com (\"jbernard1968\")",
            author_id = "161505078@N03",
            tags = "canon canoneosr5 canonrf100500mm wildlife nature focused buteojamaicensis redtailedhawk raptor hunter predator hawk facial eyes expression portrait portraiture headshot stare birdofprey canopy forest tree"
        )
    )
}