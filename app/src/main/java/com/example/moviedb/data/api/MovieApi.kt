package com.example.moviedb.data.api

import com.example.moviedb.model.details.MovieDetails
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.model.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/week")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun getMoviesByQuery(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<MovieResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetails>
}