package com.example.moviedb.data.api

import com.example.moviedb.model.authorization.RequestToken
import com.example.moviedb.model.details.MovieDetails
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.model.movie.MovieResponse
import com.example.moviedb.model.tv.TvResponse
import com.example.moviedb.model.tvDetails.TvDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun getMoviesByQuery(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<MovieResponse>

    @GET("search/tv")
    suspend fun getTvByQuery(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<TvResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetails>

    @GET("tv/{id}")
    suspend fun getTvDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
    ): Response<TvDetails>

    @GET("authentication/token/new")
    suspend fun getRequestToken(
        @Query("api_key") apiKey: String
    ): RequestToken

    @POST("authentication/token/validate_with_login")
    suspend fun validateWithLogin(
        @Query("api_key") apiKey: String,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("request_token") requestToken: String
    ): String

}