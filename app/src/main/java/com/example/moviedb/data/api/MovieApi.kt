package com.example.moviedb.data.api

import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(" ")
    suspend fun getMovies(

    ): Response<List<Movie>>

    @GET("search/movie")
    suspend fun getMoviesByQuery(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<MovieResponse>
}