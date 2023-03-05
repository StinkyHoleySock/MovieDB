package com.example.moviedb.data.repository

import com.example.moviedb.Constants
import com.example.moviedb.data.api.MovieApi
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieResponse
import retrofit2.Response
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val service: MovieApi
) : MovieRepository {

    override suspend fun getMoviesList(): Response<List<Movie>> {
        return service.getMovies()
    }

    override suspend fun getMoviesList(query: String): Response<MovieResponse> {
        return service.getMoviesByQuery(
            apiKey = Constants.API_KEY,
            query = query
        )
    }
}