package com.bardur.moviedb.ui.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.MainActivity
import com.bardur.moviedb.R
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.databinding.FragmentFavoritesBinding
import com.bardur.moviedb.storage.MovieStorageRepo
import com.bardur.moviedb.ui.adapters.MovieAdapter
import com.bardur.moviedb.ui.utills.ApplicationViewModelFactory
import com.bardur.moviedb.ui.utills.showMovieDetails


class FavoritesFragment : Fragment(), MovieAdapter.MovieClickListener {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModelFactory: ApplicationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val repo: MovieStorageRepo = (activity as MainActivity).movieStorageRepo

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        viewModelFactory =
            ApplicationViewModelFactory(movieStorageRepo = repo)

        favoritesViewModel =
            ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]

        binding.viewModel = favoritesViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter(this)
        binding.favoritesRecyclerView.adapter = movieAdapter

        /*
         * Observer the movies LiveData from the view model to make sure that
         * the displayed list of movies is updated when the LiveData changes
         */
        favoritesViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
        }
    }

    override fun handleClick(view: View, movie: Movie) {
        // The user has clicked on a movie from the list, show the details!
        showMovieDetails(view, movie)
    }
}