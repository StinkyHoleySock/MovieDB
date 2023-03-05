package com.example.moviedb.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun validateWithLogin(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            repository.validateWithLogin(
                username,
                password,
                repository.getRequestToken().request_token
            )
            _isLoading.value = false
        }
    }
}