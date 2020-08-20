package com.example.popularmovies.view_models

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.models.Movie
import com.example.popularmovies.repository.MovieRepository
import kotlinx.coroutines.launch

class PopularMoviesViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository):ViewModel() {

    private var _listMovie = MutableLiveData<List<Movie>>()
    val listMovie:LiveData<List<Movie>>
    get() = _listMovie

    init {
        getListMovie()
    }

    private fun getListMovie(){
        viewModelScope.launch {
            _listMovie.postValue(movieRepository.getMovies().results)
        }
    }
}