package com.bardur.moviedb.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bardur.moviedb.R

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val viewState = homeViewModel.viewState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Data Provided By",
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp
        )

        Image(
            painterResource(R.drawable.ic_tmdb_logo),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Latest Movie",
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )

        viewState.value.movie?.title?.let {
            Text(
                text = it,
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp
            )
        }
    }

}