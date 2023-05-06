package com.example.imdb.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb.data.db.Playlist
import com.example.imdb.data.model.MovieResponse
import com.example.imdb.data.network.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movieList = MutableLiveData<MovieResponse>()

    private val playList = MutableLiveData<List<Playlist>>()

    fun getMovies(): LiveData<MovieResponse> {
        return movieList
    }

    fun getPlaylist(): LiveData<List<Playlist>> {
        return playList
    }

    /**
     * Temp InputStream dependency used
     */
    fun fetchDataData(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.fetchDataData(inputStream)
            movieList.postValue(response)
        }
    }

    fun fetchPlaylist() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.fetchPlaylist()
            playList.postValue(list)
        }
    }

    fun createNewPlaylist(playlist: Playlist) {
        viewModelScope.launch {
            repository.createNewPlayList(playlist)
        }
    }
}