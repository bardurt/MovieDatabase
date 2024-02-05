package com.bardur.moviedb.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bardur.moviedb.ui.common.LoadingSpinner
import com.bardur.moviedb.ui.common.Navigator
import com.bardur.moviedb.ui.common.RecyclerView
import com.bardur.moviedb.ui.common.SearchBar

@Composable
fun SearchScreen(viewModel: SearchViewModel, navigator: Navigator) {
    val viewState = viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(onTextChange = { viewModel.performSearch(it) })
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (viewState.value.loading) {
                LoadingSpinner()
            } else {
                RecyclerView(movies = viewState.value.items) {
                    navigator.showMovie(it)
                }
            }
        }
    }
}