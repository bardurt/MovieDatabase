package com.bardur.moviedb.ui.screens.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo
import kotlinx.coroutines.launch

class FavoritesViewModel(private val movieStorageRepo: MovieStorageRepo) : ViewModel() {

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
        getFavoritesMovies()
    }

    private fun getFavoritesMovies() {

        _error.value = 0
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = movieStorageRepo.getAll()

                if(result.isEmpty()){
                    throw IllegalStateException("No Result Found")
                }
                _movies.value = result
            } catch (e: Exception) {
                Log.e(FavoritesViewModel::class.simpleName, e.message.orEmpty())
                _error.value = 1
            }
            _isLoading.value = false
        }

    }

}