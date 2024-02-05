package com.bardur.moviedb.ui.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val movieStorageRepo: MovieStorageRepo) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())

    val viewState: StateFlow<ViewState>
        get() = _viewState

    init {
        getFavoritesMovies()
    }

    private fun getFavoritesMovies() {
        _viewState.value = _viewState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                val result = movieStorageRepo.getAll()

                if (result.isEmpty()) {
                    throw IllegalStateException("No Result Found")
                }
                _viewState.value = _viewState.value.copy(items = result)
            } catch (e: Exception) {
                Log.e(FavoritesViewModel::class.simpleName, e.message.orEmpty())
            }
            _viewState.value = _viewState.value.copy(loading = false)
        }
    }

    data class ViewState(
        val loading: Boolean = false,
        val items: List<Movie> = listOf()
    )
}