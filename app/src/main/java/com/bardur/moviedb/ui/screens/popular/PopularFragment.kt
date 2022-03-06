package com.bardur.moviedb.ui.screens.popular

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
import com.bardur.moviedb.databinding.FragmentPopularBinding
import com.bardur.moviedb.ui.adapters.MovieAdapter
import com.bardur.moviedb.ui.screens.utills.showMovieDetails

class PopularFragment : Fragment(), MovieAdapter.MovieClickListener {

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)

        popularViewModel = ViewModelProvider(this)[PopularViewModel::class.java]

        binding.viewModel = popularViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter(this)
        binding.popularRecyclerView.adapter = movieAdapter

        /*
         * Observer the movies LiveData from the view model to make sure that
         * the displayed list of movies is updated when the LiveData changes
         */
        popularViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
        }

        /*
         * Observer the error LiveData from the view model to make sure
         * that the user is notified when an error happens
         */
        popularViewModel.error.observe(viewLifecycleOwner) { error ->
            run {
                if (error == 1) {
                    Toast.makeText(context, getString(R.string.popular_result_error_message), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun handleClick(view: View, movie: Movie) {
        // The user has clicked on a movie from the list, show the details!
        showMovieDetails(view, movie)
    }
}