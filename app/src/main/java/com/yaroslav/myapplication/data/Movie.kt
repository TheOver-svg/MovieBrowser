package com.yaroslav.myapplication.data
import androidx.room.Entity
import androidx.room.PrimaryKey
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String
)

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?
)