package com.example.moviedb.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.repository.MovieRepositoryImpl
import com.example.moviedb.model.details.MovieDetails
import com.example.moviedb.model.tvDetails.TvDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _movie = MutableLiveData<MovieDetails>()
    val movie: LiveData<MovieDetails> get() = _movie

    private val _tvs = MutableLiveData<TvDetails>()
    val tvs: LiveData<TvDetails> get() = _tvs

    fun getDetails(id: Long, isMovie: Boolean) {
        _isLoading.value = true
        viewModelScope.launch {
            if (isMovie)
                _movie.value = repository.getMovieDetails(id).body()
            else
                _tvs.value = repository.getTvDetails(id).body()

            _isLoading.value = false
        }
    }
}