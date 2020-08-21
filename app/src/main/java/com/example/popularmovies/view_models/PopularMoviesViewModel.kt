package com.example.popularmovies.view_models

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.popularmovies.data_source.MovieDataSource
import com.example.popularmovies.data_source.MovieDataSourceFactory
import com.example.popularmovies.models.Movie

class PopularMoviesViewModel @ViewModelInject constructor(dataSourceFactory: MovieDataSourceFactory) : ViewModel() {
    val moviePagedList: LiveData<PagedList<Movie>>
    private val liveDataSource: LiveData<MovieDataSource>

    init {
        liveDataSource = dataSourceFactory.movieLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MovieDataSource.PAGE_SIZE)
            .build()
        moviePagedList = LivePagedListBuilder(dataSourceFactory, config).build()
    }
}