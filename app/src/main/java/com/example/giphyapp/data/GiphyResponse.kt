package com.example.giphyapp.data

data class GiphyResponse(
    val data: List<Gif>,
    val pagination: Pagination
)

data class Pagination(
    val offset: Int,
    val count: Int,
    val total_count: Int
)