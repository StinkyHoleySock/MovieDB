package com.example.moviedb.model.tv

data class TvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_pages: Int,
    val total_results: Int
)