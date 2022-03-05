package com.bardur.moviedb.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.data.Movie

class DetailsViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}