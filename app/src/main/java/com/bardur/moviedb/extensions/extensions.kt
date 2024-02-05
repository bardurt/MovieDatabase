package com.bardur.moviedb.extensions

import java.util.*

fun Double.roundTo(
    numFractionDigits: Int
) = "%.${numFractionDigits}f".format(this, Locale.ENGLISH).toDouble()