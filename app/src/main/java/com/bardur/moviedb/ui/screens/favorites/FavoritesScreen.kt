package com.bardur.moviedb.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
fun FavoritesScreen(viewModel: FavoritesViewModel, navigator: Navigator) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
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