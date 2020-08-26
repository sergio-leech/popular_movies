package com.example.popularmovies.data_source

import androidx.paging.PageKeyedDataSource
import com.example.popularmovies.models.Movie
import com.example.popularmovies.network.TMDbService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieDataSource @Inject constructor(private val tmDbService: TMDbService) : PageKeyedDataSource<Int, Movie>() {
    private val applicationScope = CoroutineScope(Dispatchers.IO)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        applicationScope.launch {
            try {
                val responseItems = tmDbService.getPopularMoviesAsync(API_KEY, FIRST_PAGE).await().results
                responseItems.let {
                    callback.onResult(responseItems, null, FIRST_PAGE + 1)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        applicationScope.launch {
            try {
                val responseItems =
                    tmDbService.getPopularMoviesAsync(API_KEY, params.key).await().results
                val key = params.key + 1
                responseItems.let {
                    callback.onResult(responseItems, key)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        applicationScope.launch {
            try {
                val responseItems =
                    tmDbService.getPopularMoviesAsync(API_KEY, params.key).await().results
                val key = if (params.key > 1) params.key else 0
                responseItems.let {
                    callback.onResult(responseItems, key)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    companion object {
        const val API_KEY = "00786ca8bd545a54c56abbbe19170217"
        const val PAGE_SIZE = 1000
        const val FIRST_PAGE = 1
    }
}