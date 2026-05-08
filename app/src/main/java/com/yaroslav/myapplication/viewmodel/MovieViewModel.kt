package com.yaroslav.myapplication.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslav.myapplication.BuildConfig
import com.yaroslav.myapplication.data.Movie
import com.yaroslav.myapplication.data.MovieEntity
import com.yaroslav.myapplication.data.base.MovieDatabase
import com.yaroslav.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = MovieDatabase.getDatabase(application).movieDao()
    // Стан списку фільмів
    var movieList by mutableStateOf<List<Movie>>(emptyList())
        private set

    val favoriteIds = dao.getAllFavorites()
        .map { entities -> entities.map { it.id }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )
    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPopularMovies(BuildConfig.TMDB_API_KEY)
                movieList = response.results.map { dto ->
                    Movie(
                        id = dto.id,
                        title = dto.title,
                        overview = dto.overview,
                        posterUrl = "https://image.tmdb.org/t/p/w500${dto.posterPath}"
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val isFav = favoriteIds.value.contains(movie.id)
            val entity = MovieEntity(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = movie.posterUrl
            )

            if (isFav) {
                dao.deleteMovie(entity)
            } else {
                dao.insertMovie(entity)
            }
        }
    }
}