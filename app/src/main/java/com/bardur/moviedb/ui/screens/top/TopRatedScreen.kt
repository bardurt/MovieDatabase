package com.bardur.moviedb.ui.screens.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bardur.moviedb.ui.common.Navigator
import com.bardur.moviedb.ui.common.RecyclerView

@Composable
fun TopRatedScreen(viewModel: TopRatedViewModel, navigator: Navigator) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (viewState.value.loading) {
            val strokeWidth = 5.dp
            CircularProgressIndicator(
                modifier = Modifier.drawBehind {
                    drawCircle(
                        Color.Red,
                        radius = size.width / 2 - strokeWidth.toPx() / 2,
                        style = Stroke(strokeWidth.toPx())
                    )
                },
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        } else {
            RecyclerView(movies = viewState.value.items) {
                navigator.showMovie(it)
            }
        }
    }
}