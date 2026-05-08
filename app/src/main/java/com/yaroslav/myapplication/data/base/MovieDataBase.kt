package com.yaroslav.myapplication.data.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yaroslav.myapplication.data.remote.MovieDao
import com.yaroslav.myapplication.data.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}