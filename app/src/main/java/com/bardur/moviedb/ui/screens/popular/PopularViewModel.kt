package com.bardur.moviedb.ui.screens.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Int>().apply {
        value = 0
    }
    val error: LiveData<Int>
        get() = _error

    init {
        getMostPopularMovies()
    }

    private fun getMostPopularMovies() {
        _error.value = 0
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = MovieDatabaseApi.retrofitService.mostPopular()
                _movies.value = response.getSafeOnly()
            } catch (e: Exception) {
                Log.e(PopularViewModel::class.simpleName, e.message.orEmpty())
                _error.value = 1
            }
            _isLoading.value = false
        }
    }
}