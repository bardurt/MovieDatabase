package com.bardur.moviedb.ui.screens.search

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


class SearchFragment : Fragment(), Navigator {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                SearchScreen(searchViewModel, this@SearchFragment)
            }
        }
    }

    override fun showMovie(movie: Movie) {
        showMovieDetails(requireView(), movie)
    }
}