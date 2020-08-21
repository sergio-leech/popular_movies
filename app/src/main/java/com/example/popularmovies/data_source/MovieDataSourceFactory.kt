package com.example.popularmovies.data_source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.popularmovies.models.Movie
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(private val movieDataSource: MovieDataSource) :
    DataSource.Factory<Int, Movie>() {
    val movieLiveDataSource = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Movie> {
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}