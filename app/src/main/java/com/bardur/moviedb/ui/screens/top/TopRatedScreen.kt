package com.bardur.moviedb.ui.screens.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bardur.moviedb.ui.common.LoadingSpinner
import com.bardur.moviedb.ui.common.Navigator
import com.bardur.moviedb.ui.common.PagingBar
import com.bardur.moviedb.ui.common.RecyclerView

@Composable
fun TopRatedScreen(viewModel: TopRatedViewModel, navigator: Navigator) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
    ) {
        PagingBar(
            page = viewState.value.page,
            previousPage = {
                viewModel.previousPage()
            },
            nextPage = {
                viewModel.nextPage()
            }
        )
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