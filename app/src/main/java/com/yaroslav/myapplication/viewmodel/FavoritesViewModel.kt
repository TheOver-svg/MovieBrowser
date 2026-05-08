package com.yaroslav.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yaroslav.myapplication.data.base.MovieDatabase
import kotlinx.coroutines.flow.map

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
}