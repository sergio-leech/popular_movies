package com.example.popularmovies.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id")
    val id: Long,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "release_date")
    val releaseDate: String
)