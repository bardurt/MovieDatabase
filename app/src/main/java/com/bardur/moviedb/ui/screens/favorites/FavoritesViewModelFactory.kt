package com.bardur.moviedb.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.storage.MovieStorageRepo

class FavoritesViewModelFactory(private val movieStorageRepo: MovieStorageRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(movieStorageRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}