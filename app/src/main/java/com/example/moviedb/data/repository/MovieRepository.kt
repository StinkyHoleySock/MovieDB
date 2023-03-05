package com.example.moviedb.data.repository

import com.example.moviedb.model.authorization.RequestToken
import com.example.moviedb.model.details.MovieDetails
import com.example.moviedb.model.movie.MovieResponse
import com.example.moviedb.model.tv.TvResponse
import com.example.moviedb.model.tvDetails.TvDetails
import retrofit2.Response

interface MovieRepository {

    suspend fun getMoviesListByQuery(): Response<MovieResponse>

    suspend fun getMoviesListByQuery(query: String): Response<MovieResponse>

    suspend fun getTvListByQuery(query: String): Response<TvResponse>

    suspend fun getMovieDetails(id: Long): Response<MovieDetails>

    suspend fun getTvDetails(id: Long): Response<TvDetails>

    suspend fun getRequestToken(): RequestToken

    suspend fun validateWithLogin(username: String, password: String, requestToken: String): String
}