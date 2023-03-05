package com.example.moviedb.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.repository.MovieRepositoryImpl
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.model.tv.Tv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _tv = MutableLiveData<List<Tv>>()
    val tv: LiveData<List<Tv>> get() = _tv

    fun getMoviesListByQuery(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val movieResponse = repository.getMoviesListByQuery(query)
                val tvResponse = repository.getTvListByQuery(query)
                _movies.value = movieResponse.body()?.results
                _tv.value = tvResponse.body()?.results
                Log.d("develop", "mov: ${_movies.value}")
                Log.d("develop", "tvs: ${_tv.value}")
            } else {
                _movies.value = emptyList()
                _tv.value = emptyList()
            }
            _isLoading.value = false
        }
    }
}