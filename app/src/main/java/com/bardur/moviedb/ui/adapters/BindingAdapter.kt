package com.bardur.moviedb.ui.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bardur.moviedb.R
import com.bardur.moviedb.data.Rating
import com.squareup.picasso.Picasso

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("posterPath")
fun loadMoviePoster(view: ImageView, posterPath: String?) {
    posterPath
        .takeUnless { it.isNullOrEmpty() }
        .let {
            Picasso.get().load(BASE_IMAGE_URL + it).into(view)
        }
}

@BindingAdapter("app:bindRating")
fun bindRating(view: TextView, rating: Double?) {
    view.text = String.format("%.1f", rating)
}

@BindingAdapter("app:tintFavorite")
fun tintFavorite(view: ImageView, favorite: Boolean) {

    if (favorite) {
        view.setColorFilter(
            ContextCompat.getColor(view.context, R.color.red),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    } else {
        view.setColorFilter(
            ContextCompat.getColor(view.context, R.color.black),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }
}


/**
 * Binding adapter to tint the color of an image view based on a value
 * @param view : ImageView which should be tinted
 * @param value : Value of the popularity
 */
@BindingAdapter("app:tintRating")
fun tintRating(view: ImageView, value: Double) {

    val color = getAssociatedColor(Rating.convert(value), view.context)

    ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color))
}

@BindingAdapter("app:setRating")
fun setRating(view: RatingBar, value: Double) {

    val color = getAssociatedColor(Rating.convert(value), view.context)

    view.progressTintList = ColorStateList.valueOf(color)

    view.rating = value.toFloat()
}


/**
 * Fetch a color based on popularity
 * @param rating : The Popularity which should be the color base
 * @param context : Context to accessing color resources
 * @return : Color id of color for the popularity
 */
private fun getAssociatedColor(rating: Rating, context: Context): Int {
    return when (rating) {
        Rating.STAR -> ContextCompat.getColor(context, R.color.popularityStar)
        Rating.POPULAR -> ContextCompat.getColor(context, R.color.popularityPopular)
        Rating.NORMAL -> ContextCompat.getColor(context, R.color.popularityNormal)
        Rating.BAD -> ContextCompat.getColor(context, R.color.popularityBad)
    }
}