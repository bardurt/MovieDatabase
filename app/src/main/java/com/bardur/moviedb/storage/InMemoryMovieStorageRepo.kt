package com.bardur.moviedb.storage

import com.bardur.moviedb.data.Movie

class InMemoryMovieStorageRepo : MovieStorageRepo {

    private val movies = mutableListOf<Movie>()

    override fun saveMovie(movie: Movie) {
        if (movies.contains(movie)) {
            return
        }
        movies.add(movie)
    }

    override fun deleteMovie(movie: Movie) {
        movies.remove(movie)
    }

    override fun getAll(): List<Movie> {
        return movies
    }

    override fun contains(movie: Movie): Boolean {
        return movies.contains(movie)
    }
}