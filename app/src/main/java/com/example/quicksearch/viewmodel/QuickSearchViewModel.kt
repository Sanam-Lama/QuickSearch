package com.example.quickimage.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicksearch.repository.QuickSearchRepository
import com.example.quicksearch.model.Result
import com.example.quicksearch.model.asResult
import com.example.quicksearch.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * QuickSearchViewModel interacts with the repository to perform the search query, handles
 * loading, success and error states, thus providing a list of search results
 * to the UI.
 */
@HiltViewModel
class QuickSearchViewModel @Inject constructor(private val quickSearchRepository: QuickSearchRepository) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf(false)
    var searchQuery = mutableStateOf("")

    // a list that holds the search results. UI will observe the list to display
    // the search results dynamically
    val items = mutableStateListOf<Item>()

    /**
     * method to update the results in each search string.
     * this method is called to update the searchQuery state, which in
     * turn triggers the functionality or UI updates.
     */
    fun updateQuery(it: String) {
        searchQuery.value = it
    }

    /**
     * method to search the results according to the string provided
     */
    fun doSearch() {

        // this handles the empty search at the start to prevent
        // unnecessary API calls
        if (searchQuery.value.isBlank()) return

        viewModelScope.launch {
            quickSearchRepository.doSearch(searchQuery.value)
                .asResult()
                .collect {
                    when (it) {
                        is Result.Loading -> {
                            isError.value = false
                            isLoading.value = true
                        }

                        is Result.Success -> {
                            isLoading.value = false
                            items.clear()
                            items.addAll(it.data.items.orEmpty())
                        }

                        is Result.Error -> {
                            isLoading.value = false
                            items.clear()
                            isError.value = true
                        }
                    }
                }
        }

    }

}