package com.bardur.moviedb.ui.screens.details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bardur.moviedb.ui.common.HeartButton
import com.bardur.moviedb.ui.common.RatingBar


@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {

    val viewState = viewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = viewState.value.posterPath,
            contentDescription = "",
            modifier = Modifier.padding(16.dp)
        )

        RatingBar(rating = viewState.value.rating.toInt(), max = 5)

        Text(
            text = viewState.value.title,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )

        HeartButton(filled = viewState.value.favorite, onClick = {
            viewModel.updateMovieFavorite()
        })

        Text(
            text = viewState.value.overview,
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp
        )
    }

}