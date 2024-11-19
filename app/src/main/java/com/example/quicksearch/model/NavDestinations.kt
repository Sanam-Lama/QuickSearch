package com.example.quicksearch.model

import kotlinx.serialization.Serializable


@Serializable
data object Search

@Serializable
data class SearchDetail(val item: Item)