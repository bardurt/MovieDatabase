package com.bardur.moviedb.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class HomeViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())

    val viewState: StateFlow<ViewState>
        get() = _viewState

    init {
        getMostPopularMovies()
    }

    private fun getMostPopularMovies() {
        _viewState.value = viewState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                val movie = MovieDatabaseApi.retrofitService.latest()
                if (movie.adult) {
                    // We do not want to display these types of movies for the user!
                    throw IllegalStateException("Not supported!")
                }

                _viewState.value = viewState.value.copy(movie = movie)
            } catch (e: Exception) {
                _viewState.value = viewState.value.copy(error = "Movie not available!")
            }
            _viewState.value = viewState.value.copy(loading = false)
        }
    }

    data class ViewState(
        val loading: Boolean = false,
        val error: String = "",
        val movie: Movie? = null
    )
}