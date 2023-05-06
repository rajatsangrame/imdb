package com.example.imdb.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb.data.model.MovieResponse
import com.example.imdb.data.network.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movieList = MutableLiveData<MovieResponse>()

    fun getMovies(): LiveData<MovieResponse> {
        return movieList
    }

    /**
     * Temp InputStream dependency used
     */
    fun fetchDataData(inputStream: InputStream) {
        viewModelScope.launch {
            val response = repository.fetchDataData(inputStream)
            movieList.postValue(response.value)
        }
    }
}