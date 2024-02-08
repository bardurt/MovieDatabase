package com.bardur.moviedb.ui.screens.favorites

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
            val result = movieStorageRepo.getAll()
            _viewState.value = _viewState.value.copy(loading = false, items = result)
        }
    }

    data class ViewState(
        val loading: Boolean = false,
        val items: List<Movie> = listOf()
    )
}