package com.example.popularmovies.network

import com.example.popularmovies.models.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDbService {
    @GET("movie/popular")
    fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Deferred<MoviesResponse>
}