package com.example.popularmovies.adapters

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.popularmovies.R

@BindingAdapter("setImage")
fun ImageView.bindImage(imageUrl: String?) {
    load("https://image.tmdb.org/t/p/w500${imageUrl}") {
        crossfade(true)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setRating")
fun TextView.bindText(rating: Float) {
    val getRating = rating.toString()
    text = "${context.getString(R.string.rating)} $getRating"

}