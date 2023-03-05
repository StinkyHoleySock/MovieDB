package com.example.moviedb.data.repository

import com.example.moviedb.model.details.MovieDetails
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.model.movie.MovieResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getMoviesList(): Response<MovieResponse>

    suspend fun getMoviesList(query: String): Response<MovieResponse>

    suspend fun getMovieDetails(id: Int): Response<MovieDetails>
}