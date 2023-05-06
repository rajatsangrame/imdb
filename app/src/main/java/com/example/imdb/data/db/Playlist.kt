package com.example.imdb.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist")
class Playlist {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private val id: Int? = null

    @ColumnInfo(name = "name")
    private val name: String? = null

    @ColumnInfo(name = "fetch_category")
    private val songs: List<String>? = null

}