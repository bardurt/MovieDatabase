package com.bardur.moviedb.ui.screens.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())

    val viewState: StateFlow<ViewState>
        get() = _viewState

    init {
        getMostPopularMovies()
    }

    private fun getMostPopularMovies() {
        _viewState.value = _viewState.value.copy(loading = true, error = "")

        viewModelScope.launch {
            try {
                val response = MovieDatabaseApi.retrofitService.mostPopular()
                _viewState.value = _viewState.value.copy(movies = response.getSafeOnly())
            } catch (e: Exception) {
                Log.e(PopularViewModel::class.simpleName, e.message.orEmpty())
                _viewState.value = _viewState.value.copy(error = "Cannot download movies")
            }
            _viewState.value = _viewState.value.copy(loading = false)
        }
    }

    data class ViewState(
        val loading: Boolean = false,
        val movies: List<Movie> = listOf(),
        val error: String = ""
    )
}