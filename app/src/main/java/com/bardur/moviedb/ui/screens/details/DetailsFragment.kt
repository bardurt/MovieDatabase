package com.bardur.moviedb.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.MainActivity
import com.bardur.moviedb.storage.MovieStorageRepo
import com.bardur.moviedb.ui.utills.ApplicationViewModelFactory

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var viewModelFactory: ApplicationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repo: MovieStorageRepo = (activity as MainActivity).movieStorageRepo

        viewModelFactory =
            ApplicationViewModelFactory(
                movie = DetailsFragmentArgs.fromBundle(requireArguments()).movie,
                movieStorageRepo = repo
            )

        detailsViewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                DetailsScreen(detailsViewModel)
            }
        }
    }
}