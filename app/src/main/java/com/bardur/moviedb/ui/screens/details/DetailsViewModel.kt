package com.bardur.moviedb.ui.screens.details

import androidx.lifecycle.ViewModel
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel(private val movie: Movie, private val movieStorageRepo: MovieStorageRepo) :
    ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())

    val viewState: StateFlow<ViewState>
        get() = _viewState

    init {
        _viewState.value = _viewState.value.copy(
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.getPosterUrl(),
            releaseYear = movie.releaseYear(),
            rating = movie.toFiveStarRating(),
            favorite = movieStorageRepo.contains(movie)
        )
    }

    private fun checkIfFavorite(): Boolean {
        return movieStorageRepo.contains(movie)
    }

    private fun updateFavoriteValue() {
        _viewState.value = _viewState.value.copy(
            favorite = movieStorageRepo.contains(movie)
        )
    }

    fun updateMovieFavorite() {
        if (checkIfFavorite()) {
            removeFavorite()
        } else {
            saveAsFavorite()
        }
    }

    private fun saveAsFavorite() {
        movieStorageRepo.save(movie)
        updateFavoriteValue()
    }

    private fun removeFavorite() {
        movieStorageRepo.delete(movie)
        updateFavoriteValue()
    }

    data class ViewState(
        val title: String = "",
        val overview: String = "",
        val posterPath: String = "",
        val releaseYear: String = "",
        val rating: Double = 0.0,
        val favorite: Boolean = false
    )
}