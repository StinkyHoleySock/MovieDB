package com.example.moviedb.data.repository

import com.example.moviedb.util.Constants
import com.example.moviedb.data.api.MovieApi
import com.example.moviedb.model.authorization.RequestToken
import com.example.moviedb.model.details.MovieDetails
import com.example.moviedb.model.movie.MovieResponse
import retrofit2.Response
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val service: MovieApi
) : MovieRepository {

    override suspend fun getMoviesList(): Response<MovieResponse> {
        return service.getMovies(
            apiKey = Constants.API_KEY
        )
    }

    override suspend fun getMoviesList(query: String): Response<MovieResponse> {
        return service.getMoviesByQuery(
            apiKey = Constants.API_KEY,
            query = query
        )
    }

    override suspend fun getMovieDetails(id: Long): Response<MovieDetails> {
        return service.getMovieDetails(
            id = id,
            apiKey = Constants.API_KEY
        )
    }

    override suspend fun getRequestToken(): RequestToken {
        return service.getRequestToken(
            apiKey = Constants.API_KEY
        )
    }

    override suspend fun validateWithLogin(
        username: String, password: String, requestToken: String
    ): String {
        return service.validateWithLogin(
            apiKey = Constants.API_KEY,
            username = username,
            password = password,
            requestToken = requestToken
        )
    }

}