package com.bardur.moviedb.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.data.ProductionCountry
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class HomeViewModel : ViewModel(){
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

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
                val movie = MovieDatabaseApi.retrofitService.latest()
                if(movie.adult){
                    throw IllegalStateException("Not supported!")
                }
                _movie.value = movie
            } catch (e: Exception) {
                _error.value = 1
            }
            _isLoading.value = false
        }
    }
}