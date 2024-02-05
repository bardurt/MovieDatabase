package com.bardur.moviedb.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())

    val viewState: StateFlow<ViewState>
        get() = _viewState

    fun performSearch(query: String) {

        _viewState.value = _viewState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            try {
                val response = MovieDatabaseApi.retrofitService.search(query)
                _viewState.value = _viewState.value.copy(items = response.getSafeOnly())
            } catch (e: Exception) {
                _viewState.value = _viewState.value.copy(error = "Cannot find movie")
            }
            _viewState.value = _viewState.value.copy(loading = false)
        }
    }

    data class ViewState(
        val loading: Boolean = false,
        val error: String = "",
        val items: List<Movie> = listOf()
    )
}