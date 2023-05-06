package com.example.imdb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Playlist::class], version = 1)
@TypeConverters(DbTypeConverter::class)
abstract class PlaylistDatabase : RoomDatabase() {

    abstract fun playListDao(): PlaylistDao

    companion object {
        @Volatile
        private var INSTANCE: PlaylistDatabase? = null

        fun getDataBase(context: Context): PlaylistDatabase? {
            if (INSTANCE == null) {
                synchronized(PlaylistDatabase::class) {
                    if (INSTANCE == null)
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            PlaylistDatabase::class.java, "database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}
