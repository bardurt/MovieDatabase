package com.bardur.moviedb.storage

import com.bardur.moviedb.data.Movie

interface MovieStorageRepo {

    fun saveMovie(movie : Movie)

    fun deleteMovie(movie: Movie)

    fun getAll() : List<Movie>
}