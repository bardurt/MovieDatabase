package com.bardur.moviedb.data

import com.squareup.moshi.Json
import java.io.Serializable
import java.lang.Exception

data class Movie(
    val title: String,
    val adult: Boolean,
    @Json(name = "poster_path")
    val posterPath: String?,
    val overview: String,
    @Json(name = "vote_average")
    val vote: Double,
    @Json(name = "release_date")
    val releaseDate: String,
    val tagline: String?,
    val status: String?,
    val runtime: String?
) : Serializable {


    fun toFiveStarRating(): Double {

        val percent = vote / MAX_RATING

        return FIVE_SCALE * percent
    }

    fun releaseYear(): String {
        return try {
            releaseDate.split("-")[0]
        } catch (e: Exception) {
            ""
        }
    }

    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    companion object {
        const val MAX_RATING = 10
        const val FIVE_SCALE = 5
    }
}