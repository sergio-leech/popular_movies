package com.example.popularmovies.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.popularmovies.R

@BindingAdapter("setImage")
fun bindImage(imageView: ImageView, imageUrl: String?){
    imageView.load("https://image.tmdb.org/t/p/w342${imageUrl}"){
        crossfade(true)
        placeholder(R.drawable.ic_cinema)
    }

}

/*@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/w500${imageUrl}")
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}*/
