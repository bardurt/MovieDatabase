package com.bardur.moviedb.ui.screens.utills

import android.view.View
import androidx.navigation.findNavController
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.ui.screens.details.DetailsFragmentDirections

fun showMovieDetails(view: View, movie: Movie) {
    view.findNavController().navigate(DetailsFragmentDirections.actionMovieDetails(movie))
}
