package com.bardur.moviedb.ui.screens.top

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bardur.moviedb.api.MovieDatabaseApi
import com.bardur.moviedb.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TopRatedViewModel : ViewModel() {

    private var currentPage = 1

    private val _viewState = MutableStateFlow(ViewState())

    val viewState: StateFlow<ViewState>
        get() = _viewState

    init {
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() {
        _viewState.value = _viewState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            try {
                val response = MovieDatabaseApi.retrofitService.topRated(currentPage)
                _viewState.value =
                    _viewState.value.copy(items = response.getSafeOnly(), page = currentPage)
            } catch (e: Exception) {
                Log.e(TopRatedViewModel::class.simpleName, e.message.orEmpty())
                _viewState.value = _viewState.value.copy(error = "Cannot download movies")
            }
            _viewState.value = _viewState.value.copy(loading = false)
        }
    }

    fun nextPage() {
        currentPage++
        getTopRatedMovies()
    }

    fun previousPage() {
        currentPage--
        if (currentPage > 0) {
            getTopRatedMovies()
        } else {
            currentPage = 1
        }
    }

    data class ViewState(
        val loading: Boolean = false,
        val page: Int = 1,
        val error: String = "",
        val items: List<Movie> = listOf()
    )
}