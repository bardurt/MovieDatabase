package com.bardur.moviedb.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bardur.moviedb.R
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.extensions.roundTo
import androidx.compose.runtime.setValue


@Composable
fun RatingBar(rating: Int, max: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..rating) {
            Image(
                painterResource(R.drawable.ic_star_black_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = Color.Yellow)
            )
        }

        for (i in rating + 1..max) {
            Image(
                painterResource(R.drawable.ic_star_black_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = Color.Gray)
            )
        }
    }
}


@Composable
fun StarBar(rating: Double) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.ic_star_black_24),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = Color.Yellow)
        )
        Text(
            text = rating.roundTo(1).toString(),
            fontSize = 10.sp
        )
    }
}

@Composable
fun HeartButton(filled: Boolean, onClick: () -> Unit) {
    Image(
        modifier = Modifier.clickable { onClick.invoke() },
        painter = painterResource(R.drawable.ic_star_black_24),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(color = if (filled) Color.Red else Color.Gray),
    )
}

@Composable
fun RecyclerView(movies: List<Movie>, onClick: (movie: Movie) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) {
            MovieCardView(movie = it, onClick = { movie ->
                onClick.invoke(movie)
            })
        }
    }
}

@Composable
fun MovieCardView(movie: Movie, onClick: (movie: Movie) -> Unit) {
    Card(
        modifier = Modifier.height(136.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(movie) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.getPosterUrl(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(0.dp, 0.dp, 4.dp, 0.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(22.dp))
                StarBar(rating = movie.toFiveStarRating())
            }
        }
    }
}

@Composable
fun LoadingSpinner(stroke: Int = 5) {
    val strokeWidth = stroke.dp
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
}

@Composable
fun SearchBar(onTextChange: (text: String) -> Unit) {

    var text by remember {
        mutableStateOf("")
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_black_24),
            contentDescription = "Vector",
            colorFilter = ColorFilter.tint(color = Color.Black),
            modifier = Modifier
                .width(width = 24.dp)
                .height(height = 24.dp)
        )
        TextField(
            value = text,
            onValueChange = {
                text = it
                onTextChange.invoke(it)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}