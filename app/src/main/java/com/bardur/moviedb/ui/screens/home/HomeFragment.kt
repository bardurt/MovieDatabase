package com.bardur.moviedb.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.R
import com.bardur.moviedb.databinding.FragmentHomeBinding
import com.bardur.moviedb.ui.adapters.MovieAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.viewModel = homeViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.error.observe(viewLifecycleOwner) { error ->
            run {
                if (error == 1) {
                    Toast.makeText(
                        context,
                        getString(R.string.latest_result_error_message),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

}