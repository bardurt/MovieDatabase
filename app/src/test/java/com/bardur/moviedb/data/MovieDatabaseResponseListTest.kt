package com.bardur.moviedb.data

import org.junit.Assert
import org.junit.Test

class MovieDatabaseResponseListTest {

    @Test
    fun `Given a list of movies with only unsafe results, When fetching safe results only, Then return empty list `() {

        val movie1 = Movie(
            title = "a",
            adult = true,
            posterPath = "",
            overview = "",
            vote = 0.0,
            releaseDate = "",
            tagline = "",
            status = "",
            runtime = ""
        )

        val movie2 = Movie(
            title = "b",
            adult = true,
            posterPath = "",
            overview = "",
            vote = 0.0,
            releaseDate = "",
            tagline = "",
            status = "",
            runtime = ""
        )


        val response = MovieDatabaseResponseList(
            listOf(
                movie1,
                movie2
            )
        )

        val result = response.getSafeOnly()

        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun `Given a list of movies with unsafe results, When fetching safe results only, Then return a list with safe result only`() {

        val movie1 = Movie(
            title = "a",
            adult = false,
            posterPath = "",
            overview = "",
            vote = 0.0,
            releaseDate = "",
            tagline = "",
            status = "",
            runtime = ""
        )

        val movie2 = Movie(
            title = "b",
            adult = true,
            posterPath = "",
            overview = "",
            vote = 0.0,
            releaseDate = "",
            tagline = "",
            status = "",
            runtime = ""
        )


        val response = MovieDatabaseResponseList(
            listOf(
                movie1,
                movie2
            )
        )

        val result = response.getSafeOnly()

        Assert.assertFalse(result.isEmpty())
        Assert.assertTrue(result.containsAll(listOf(movie1)))
    }

    @Test
    fun `Given a list of movies with unsafe results, When fetching all, Then return a list with all`() {

        val movie1 = Movie(
            title = "a",
            adult = false,
            posterPath = "",
            overview = "",
            vote = 0.0,
            releaseDate = "",
            tagline = "",
            status = "",
            runtime = ""
        )

        val movie2 = Movie(
            title = "b",
            adult = true,
            posterPath = "",
            overview = "",
            vote = 0.0,
            releaseDate = "",
            tagline = "",
            status = "",
            runtime = ""
        )


        val response = MovieDatabaseResponseList(
            listOf(
                movie1,
                movie2
            )
        )

        val result = response.getAll()

        Assert.assertFalse(result.isEmpty())
        Assert.assertTrue(result.containsAll(listOf(movie1, movie2)))
    }
}