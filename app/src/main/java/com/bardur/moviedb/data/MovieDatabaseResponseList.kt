package com.bardur.moviedb.data

data class MovieDatabaseResponseList(private val results: List<Movie>) {

    fun getSafeOnly(): List<Movie> {
        return results.filter { !it.adult }
    }

    fun getAll(): List<Movie> {
        return results
    }
}