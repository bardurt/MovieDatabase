package com.bardur.moviedb.ui.screens.popular

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

class PopularFragment : Fragment(), Navigator {

    private lateinit var popularViewModel: PopularViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        popularViewModel = ViewModelProvider(this)[PopularViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setContent {
                PopularScreen(popularViewModel, this@PopularFragment)
            }
        }
    }

    override fun showMovie(movie: Movie) {
        showMovieDetails(requireView(), movie)
    }
}