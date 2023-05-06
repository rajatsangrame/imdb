package com.example.imdb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playlist: Playlist): Long

    @Query("SELECT * FROM playlist")
    fun getAllPlaylist(): List<Playlist>

    @Update()
    fun update(playlist: Playlist)

}
