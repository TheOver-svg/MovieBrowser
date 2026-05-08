package com.yaroslav.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslav.myapplication.data.Movie
import com.yaroslav.myapplication.data.base.MovieDatabase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = MovieDatabase.getDatabase(application).movieDao()

    val favoriteMovies = dao.getAllFavorites().map { entities ->
        entities.map { entity ->
            com.yaroslav.myapplication.data.Movie(
                id = entity.id,
                title = entity.title,
                overview = entity.overview,
                posterUrl = entity.posterPath ?: ""
            )
        }
    }
    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            dao.deleteMovie(
                com.yaroslav.myapplication.data.MovieEntity(
                    movie.id, movie.title, movie.overview, movie.posterUrl
                )
            )
        }
    }
}