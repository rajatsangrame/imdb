package com.example.imdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.R
import com.example.imdb.data.db.Playlist
import com.example.imdb.data.db.PlaylistDatabase
import com.example.imdb.data.model.FetchPlaylistResult
import com.example.imdb.data.network.MovieRepository
import com.example.imdb.databinding.ActivityMainBinding
import com.example.imdb.ui.bottomsheet.PlayListBottomSheet
import com.example.imdb.util.CustomViewModelFactory
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private val mainViewModel: MainViewModel by viewModels {
        val dao = PlaylistDatabase.getDataBase(this)!!.playListDao()
        val repository = MovieRepository(dao)
        CustomViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupData()
    }

    private fun setupData() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter {
            mainViewModel.fetchPlaylist(it.title)
        }
        binding.recyclerView.adapter = movieAdapter
        val inputStream = resources.openRawResource(R.raw.response)
        mainViewModel.fetchDataData(inputStream)
    }

    private fun loadBottomSheet(result: FetchPlaylistResult) {
        val bottomSheet = PlayListBottomSheet.newInstance(
            playlist = result.playlist,
            callback = object : PlayListBottomSheet.OptionCLickListener {
                override fun save(playlist: Playlist) {
                    if (playlist.movies.isNullOrEmpty()) {
                        playlist.movies = mutableListOf(result.movieToBeAdded)
                    }
                    mainViewModel.updatePlaylist(playlist)
                }

                override fun new() {
                    mainViewModel.createNewPlaylist(Playlist("name", mutableListOf(result.movieToBeAdded)))

                }
            }
        )
        bottomSheet.show(supportFragmentManager, PlayListBottomSheet.TAG)
    }

    private fun setupObservers() {
        mainViewModel.getMovies().observe(this) {
            it?.let {
                Log.d(TAG, "setupObservers: ${it.results.size}")
                movieAdapter.setList(it.results)
            }
        }
        mainViewModel.getPlaylist().observe(this) {
            it?.let {
                loadBottomSheet(it)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}