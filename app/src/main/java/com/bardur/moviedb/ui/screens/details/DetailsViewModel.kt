package com.bardur.moviedb.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo

class DetailsViewModel(val movie: Movie, private val moveStorageRepo: MovieStorageRepo) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String>
        get() = _overview

    private val _posterPath = MutableLiveData<String>()
    val posterPath: LiveData<String>
        get() = _posterPath

    private val _saveResult = MutableLiveData<Int>().apply {
        value = 0
    }
    val saveResult: LiveData<Int>
        get() = _saveResult

    init {
        _title.value = movie.title
        _overview.value = movie.overview
        _posterPath.value = movie.posterPath
    }


    fun saveMovie() {
        _saveResult.value = 0
        moveStorageRepo.saveMovie(movie)
        _saveResult.value = 1
    }
}