package com.example.imdb.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imdb.data.db.Playlist
import com.example.imdb.data.db.PlaylistDao
import com.example.imdb.data.model.MovieResponse
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class MovieRepository(
    private val playlistDao: PlaylistDao
) {

    suspend fun fetchDataData(inputStream: InputStream, page: Int = 0): LiveData<MovieResponse> {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
        }
        val liveData = MutableLiveData<MovieResponse>()
        val response = Gson().fromJson(outputStream.toString(), MovieResponse::class.java)
        liveData.value = response
        return liveData
    }

    suspend fun getAllPlaylist(): List<Playlist>? {
        return playlistDao?.getAllPlaylist()
    }

    suspend fun createNewPlayList(playlist: Playlist) {
        playlistDao?.insert(playlist)
    }

}