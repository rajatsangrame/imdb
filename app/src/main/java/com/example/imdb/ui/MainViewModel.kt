package com.example.imdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb.data.db.Playlist
import com.example.imdb.data.model.FetchPlaylistResult
import com.example.imdb.data.model.MovieResponse
import com.example.imdb.data.network.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movieList = MutableLiveData<MovieResponse>()

    private val playList = MutableLiveData<FetchPlaylistResult>()

    fun getMovies(): LiveData<MovieResponse> {
        return movieList
    }

    fun getPlaylist(): LiveData<FetchPlaylistResult> {
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

    fun fetchPlaylist(movieToBeAdded: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.fetchPlaylist()
            playList.postValue(FetchPlaylistResult(movieToBeAdded, list))
        }
    }

    fun createNewPlaylist(playlist: Playlist) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewPlayList(playlist)
        }
    }

    fun updatePlaylist(playlist: Playlist) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayList(playlist)
        }
    }
}