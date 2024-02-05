package com.bardur.moviedb.ui.common

import com.bardur.moviedb.data.Movie

interface Navigator {
    fun showMovie(movie: Movie)
}