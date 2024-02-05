package com.bardur.moviedb.ui.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.MainActivity
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.storage.MovieStorageRepo
import com.bardur.moviedb.ui.common.Navigator
import com.bardur.moviedb.ui.utills.ApplicationViewModelFactory
import com.bardur.moviedb.ui.utills.showMovieDetails


class FavoritesFragment : Fragment(), Navigator {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var viewModelFactory: ApplicationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repo: MovieStorageRepo = (activity as MainActivity).movieStorageRepo
        viewModelFactory = ApplicationViewModelFactory(movieStorageRepo = repo)

        favoritesViewModel =
            ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                FavoritesScreen(favoritesViewModel, this@FavoritesFragment)
            }
        }
    }

    override fun showMovie(movie: Movie) {
        showMovieDetails(requireView(), movie)
    }
}