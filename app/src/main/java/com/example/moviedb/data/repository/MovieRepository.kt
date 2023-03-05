package com.example.moviedb.data.repository

import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieResponse
import retrofit2.Response

interface MovieRepository {
    suspend fun getMoviesList(): Response<List<Movie>>

    suspend fun getMoviesList(query: String): Response<MovieResponse>
}