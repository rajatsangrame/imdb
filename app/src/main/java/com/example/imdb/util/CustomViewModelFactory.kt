package com.example.imdb.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.data.network.MovieRepository
import com.example.imdb.ui.MainViewModel


class CustomViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Handle the rest cases for view model
        return MainViewModel(repository) as T
    }
}
