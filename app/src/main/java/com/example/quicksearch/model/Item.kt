package com.example.quicksearch.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val author: String?,
    val author_id: String?,
    val date_taken: String?,
    val description: String?,
    val link: String?,
    val media: Media?,
    val published: String?,
    val tags: String?,
    val title: String?
) {
    fun mediaUrl() = media?.m
}