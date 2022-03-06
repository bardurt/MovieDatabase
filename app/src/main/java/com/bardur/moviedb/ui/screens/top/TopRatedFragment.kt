package com.bardur.moviedb.ui.screens.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.R
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.databinding.FragmentTopRatedBinding
import com.bardur.moviedb.ui.adapters.MovieAdapter
import com.bardur.moviedb.ui.screens.utills.showMovieDetails


class TopRatedFragment : Fragment(), MovieAdapter.MovieClickListener,
    MovieAdapter.MovieDataListener {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var binding: FragmentTopRatedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated, container, false)

        topRatedViewModel = ViewModelProvider(this)[TopRatedViewModel::class.java]

        binding.viewModel = topRatedViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter(this, this)
        binding.topRatedRecyclerView.adapter = movieAdapter

        /*
         * Observer the movies LiveData from the view model to make sure that
         * the displayed list of movies is updated when the LiveData changes
         */
        topRatedViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
        }

        /*
         * Observer the error LiveData from the view model to make sure
         * that the user is notified when an error happens
         */
        topRatedViewModel.error.observe(viewLifecycleOwner) { error ->
            run {
                if (error == 1) {
                    Toast.makeText(
                        context,
                        getString(R.string.top_rated_result_error_message),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun handleClick(view: View, movie: Movie) {
        // The user has clicked on a movie from the list, show the details!
        showMovieDetails(view, movie)
    }

    override fun onDataUpdated() {
        // Data in the list has been updated, makes sure to scroll the list to the top!
        binding.topRatedRecyclerView.scrollToPosition(0)
    }
}