package com.bardur.moviedb.ui.screens.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.launch

class TopRatedViewModel : ViewModel() {

    private var currentPage = 1

    private val _page = MutableLiveData<Int>().apply {
        value = currentPage
    }
    val page: LiveData<Int>
        get() = _page

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private var loading = false
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = loading
    }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Int>().apply {
        value = 0
    }
    val error: LiveData<Int>
        get() = _error

    init {
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() {
        loading = true
        _error.value = 0
        _isLoading.value = loading
        viewModelScope.launch {
            try {
                val response = MovieDatabaseApi.retrofitService.topRated(currentPage)
                _movies.value = response.getSafeOnly()
                _page.value = currentPage
            } catch (e: Exception) {
                Log.e(TopRatedViewModel::class.simpleName, e.message.orEmpty())
                _error.value = 1
            }
            loading = false
            _isLoading.value = loading
        }
    }

    fun nextPage() {
        if (!loading) {
            currentPage++
            getTopRatedMovies()
        }
    }

    fun previousPage() {
        if (!loading) {
            currentPage--
            if (currentPage > 0) {
                getTopRatedMovies()
            } else {
                currentPage = 1
            }
        }
    }
}