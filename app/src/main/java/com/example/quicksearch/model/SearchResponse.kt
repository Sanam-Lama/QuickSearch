package com.example.quicksearch.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val description: String? = null,
    val generator: String? = null,
    val items: List<Item>? = null,
    val link: String? = null,
    val modified: String? = null,
    val title: String? = null,
)