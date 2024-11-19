package com.example.quicksearch.repository

import com.example.quicksearch.model.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuickSearchRepository @Inject constructor(private val quickSearchApi: QuickSearchApi) {

    fun doSearch(tags: String): Flow<SearchResponse> {
        return flow {
            emit(quickSearchApi.doSearch(tags = tags))
        }.flowOn(Dispatchers.IO)
    }
}

