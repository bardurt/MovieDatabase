package com.bardur.moviedb.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo

class DetailsViewModelFactory(
    private val movie: Movie,
    private val movieStorageRepo: MovieStorageRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(movie, movieStorageRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}