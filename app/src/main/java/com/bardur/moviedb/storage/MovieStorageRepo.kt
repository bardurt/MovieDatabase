package com.bardur.moviedb.storage

import com.bardur.moviedb.data.Movie

interface MovieStorageRepo {

    fun save(movie : Movie)

    fun delete(movie: Movie)

    fun getAll() : List<Movie>

    fun contains(movie: Movie) : Boolean
}

class InMemoryMovieStorageRepo : MovieStorageRepo {

    private val movies = mutableListOf<Movie>()

    override fun save(movie: Movie) {
        if (movies.contains(movie)) {
            return
        }
        movies.add(movie)
    }

    override fun delete(movie: Movie) {
        movies.remove(movie)
    }

    override fun getAll(): List<Movie> {
        return movies
    }

    override fun contains(movie: Movie): Boolean {
        return movies.contains(movie)
    }
}