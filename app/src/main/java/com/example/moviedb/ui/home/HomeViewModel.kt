package com.example.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.repository.MovieRepositoryImpl
import com.example.moviedb.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        _isLoading.value = true
        viewModelScope.launch {
            val response = repository.getMoviesList()
            _movies.value = response.body()
            _isLoading.value = false
        }
    }
}