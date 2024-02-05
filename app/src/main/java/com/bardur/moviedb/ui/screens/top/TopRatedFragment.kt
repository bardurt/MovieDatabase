package com.bardur.moviedb.ui.screens.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.ui.common.Navigator
import com.bardur.moviedb.ui.utills.showMovieDetails


class TopRatedFragment : Fragment(), Navigator {

    private lateinit var topRatedViewModel: TopRatedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        topRatedViewModel = ViewModelProvider(this)[TopRatedViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                TopRatedScreen(topRatedViewModel, this@TopRatedFragment)
            }
        }
    }

    override fun showMovie(movie: Movie) {
        showMovieDetails(requireView(), movie)
    }
}