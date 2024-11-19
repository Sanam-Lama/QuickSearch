package com.example.quicksearch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.quicksearch.model.Item
import com.example.quickimage.viewmodel.QuickSearchViewModel
import com.example.quicksearch.R

/**
 * this class is used to handle the state for a search feature, such as
 * displaying a loading indicator, showing error results and rendering
 * search results in the grid.
 */
@Composable
fun SearchScreen(
    onItemClick: (Item) -> Unit,
    viewModel: QuickSearchViewModel = hiltViewModel()
) {
    val listScope = rememberLazyGridState()

    val searchQuery by remember {
        viewModel.searchQuery
    }

    val images = viewModel.items

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                viewModel.updateQuery(it)
                viewModel.doSearch()
            },
            placeholder = { Text(text = stringResource(R.string.text_search)) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RectangleShape)
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(),
            singleLine = true
        )
        if (viewModel.isLoading.value) {
            Spacer(Modifier.height(25.dp))
            CircularProgressIndicator()
        } else if (viewModel.isError.value) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 50.dp),
                text = stringResource(R.string.text_something_went_wrong)
            )
        } else {
            if (images.isNotEmpty()) {
                LazyVerticalGrid(
                    state = listScope,
                    columns = GridCells.Fixed(2), // 2 columns for grid
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(images) {
                        AsyncImage(
                            model = it.mediaUrl() ?: R.drawable.img,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(150.dp)
                                .clickable {
                                    onItemClick(it)
                                }
                                .background(Color.LightGray, RoundedCornerShape(15.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            } else {
                if (viewModel.searchQuery.value.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 50.dp),
                        text = stringResource(R.string.text_search_something)
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 50.dp),
                        text = stringResource(R.string.text_no_data_found)
                    )
                }
            }
        }

    }
}