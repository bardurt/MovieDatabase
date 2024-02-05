package com.bardur.moviedb.ui.screens.popular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bardur.moviedb.ui.common.LoadingSpinner
import com.bardur.moviedb.ui.common.Navigator
import com.bardur.moviedb.ui.common.RecyclerView

@Composable
fun PopularScreen(viewModel: PopularViewModel, navigator: Navigator) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (viewState.value.loading) {
            LoadingSpinner()
        } else {
            RecyclerView(movies = viewState.value.movies) {
                navigator.showMovie(it)
            }
        }
    }
}