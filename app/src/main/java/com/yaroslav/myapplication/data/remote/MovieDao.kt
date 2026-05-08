package com.yaroslav.myapplication.data.remote
import androidx.room.*
import com.yaroslav.myapplication.data.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}