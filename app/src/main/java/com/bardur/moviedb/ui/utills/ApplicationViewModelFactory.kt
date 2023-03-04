package com.bardur.moviedb.ui.utills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo
import com.bardur.moviedb.ui.screens.details.DetailsViewModel
import com.bardur.moviedb.ui.screens.favorites.FavoritesViewModel

class ApplicationViewModelFactory(
    private val movie: Movie? = null,
    private val movieStorageRepo: MovieStorageRepo? = null
) : ViewModelProvider.AndroidViewModelFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            if (movie == null) {
                throw IllegalArgumentException("Movie cannot be null")
            }

            if (movieStorageRepo == null) {
                throw IllegalArgumentException("MovieStorageRepo cannot be null")
            }

            return DetailsViewModel(movie, movieStorageRepo) as T
        }


        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {

            if (movieStorageRepo == null) {
                throw IllegalArgumentException("MovieStorageRepo cannot be null")
            }
            return FavoritesViewModel(movieStorageRepo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}