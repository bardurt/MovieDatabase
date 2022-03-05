package com.bardur.moviedb.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bardur.moviedb.data.Movie

class DetailsViewModel(val movie: Movie) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String>
        get() = _overview

    private val _posterPath = MutableLiveData<String>()
    val posterPath: LiveData<String>
        get() = _posterPath

    init {
        _title.value = movie.title
        _overview.value = movie.overview
        _posterPath.value = movie.posterPath
    }
}