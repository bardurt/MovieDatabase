package com.bardur.moviedb.data

enum class Rating {
    STAR,
    POPULAR,
    NORMAL,
    BAD;

    companion object {
        fun convert(value: Double): Rating {
            return when {
                value > 4.1 -> {
                    STAR
                }
                value > 3.7 -> {
                    POPULAR
                }
                value > 3 -> {
                    NORMAL
                }
                else -> {
                    BAD
                }
            }
        }
    }
}