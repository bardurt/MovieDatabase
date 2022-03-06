package com.bardur.moviedb.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Int>().apply {
        value = 0
    }

    val error: LiveData<Int>
        get() = _error

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun performSearch(query: String) {
        _error.value = 0
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = MovieDatabaseApi.retrofitService.search(query)
                _movies.value = response.getSafeOnly()

            } catch (e: Exception) {
                _error.value = 1
            }

            _isLoading.value = false
        }
    }
}