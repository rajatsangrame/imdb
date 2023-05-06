package com.example.imdb.data.model

import com.example.imdb.data.db.Playlist

data class FetchPlaylistResult(
    val movieToBeAdded: String,
    val playlist: List<Playlist>
)

