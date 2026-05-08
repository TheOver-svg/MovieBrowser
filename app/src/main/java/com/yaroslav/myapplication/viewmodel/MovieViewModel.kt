package com.yaroslav.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslav.myapplication.BuildConfig
import com.yaroslav.myapplication.data.Movie
import com.yaroslav.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    // Стан списку фільмів
    var movieList by mutableStateOf<List<Movie>>(emptyList())
        private set

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
}