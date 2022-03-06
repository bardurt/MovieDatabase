package com.bardur.moviedb.ui.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.R
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.databinding.FragmentSearchBinding
import com.bardur.moviedb.ui.adapters.MovieAdapter
import com.bardur.moviedb.ui.screens.utills.showMovieDetails


class SearchFragment : Fragment(), SearchView.OnQueryTextListener,
    MovieAdapter.MovieClickListener,
    MovieAdapter.MovieDataListener {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        binding.searchViewModel = searchViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = binding.searchSearchView
        searchView.setOnQueryTextListener(this)

        val movieAdapter = MovieAdapter(this, this)
        binding.searchRecyclerView.adapter = movieAdapter

        /*
         * Observer the movies LiveData from the view model to make sure that
         * the displayed list of movies is updated when the LiveData changes
         */
        searchViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
        }

        /*
         * Observer the error LiveData from the view model to make sure
         * that the user is notified when an error happens
         */
        searchViewModel.error.observe(viewLifecycleOwner) { error ->
            if (error == 1) {
                Toast.makeText(
                    context,
                    getString(R.string.search_result_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query.takeUnless { it.isNullOrEmpty() }?.let {
            searchViewModel.performSearch(it)
            searchView.clearFocus()
            return true
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean = true

    override fun handleClick(view: View, movie: Movie) {
        // The user has clicked on a movie from the list, show the details!
        showMovieDetails(view, movie)
    }

    override fun onDataUpdated() {
        // Data in the list has been updated, makes sure to scroll the list to the top!
        binding.searchRecyclerView.scrollToPosition(0)
    }
}