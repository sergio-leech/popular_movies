package com.example.popularmovies.repository

import com.example.popularmovies.models.MoviesResponse
import com.example.popularmovies.network.API_KEY
import com.example.popularmovies.network.TMDbService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val tmDbService: TMDbService) {
      suspend fun getMovies():MoviesResponse{
          return tmDbService.getPopularMoviesAsync(API_KEY,4).await()
      }
}