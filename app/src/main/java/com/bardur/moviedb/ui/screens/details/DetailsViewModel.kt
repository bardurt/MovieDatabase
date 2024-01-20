package com.bardur.moviedb.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo

class DetailsViewModel(private val movie: Movie, private val moveStorageRepo: MovieStorageRepo) :
    ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String>
        get() = _overview

    private val _posterPath = MutableLiveData<String>()
    val posterPath: LiveData<String>
        get() = _posterPath

    private val _releaseYear = MutableLiveData<String>()
    val releaseYear : LiveData<String>
        get() = _releaseYear

    private val _rating = MutableLiveData<Double>()
    val rating : LiveData<Double>
        get() =  _rating

    private val _favorite = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isFavorite: LiveData<Boolean>
        get() = _favorite

    init {
        _title.value = movie.title
        _overview.value = movie.overview
        movie.posterPath?.let {
            _posterPath.value = it
        }
        _releaseYear.value = movie.releaseYear()
        _rating.value = movie.toFiveStarRating()
        updateFavoriteValue()
    }

    private fun checkIfFavorite(): Boolean {
        return moveStorageRepo.contains(movie)
    }

    private fun updateFavoriteValue() {
        _favorite.value = moveStorageRepo.contains(movie)
    }

    fun updateMovieFavorite() {
        if (checkIfFavorite()) {
            removeFavorite()
        } else {
            saveAsFavorite()
        }
    }

    private fun saveAsFavorite() {
        moveStorageRepo.save(movie)
        updateFavoriteValue()
    }

    private fun removeFavorite() {
        moveStorageRepo.delete(movie)
        updateFavoriteValue()
    }
}