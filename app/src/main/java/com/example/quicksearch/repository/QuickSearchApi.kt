package com.example.quicksearch.repository

import com.example.quicksearch.model.SearchResponse
import com.example.quicksearch.base.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface QuickSearchApi {

    @GET(Constants.FEEDS_URL)
    suspend fun doSearch(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") callback: String = "1",
        @Query("tags") tags: String,
    ): SearchResponse
}