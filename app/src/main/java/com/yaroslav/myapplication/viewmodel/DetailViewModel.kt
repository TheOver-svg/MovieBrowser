package com.yaroslav.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslav.myapplication.BuildConfig
import com.yaroslav.myapplication.data.MovieDetails
import com.yaroslav.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    var movieDetails by mutableStateOf<MovieDetails?>(null)
        private set

    var isLoading by mutableStateOf(true)
        private set

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                val dto = RetrofitClient.apiService.getMovieDetails(
                    movieId = movieId,
                    apiKey = BuildConfig.TMDB_API_KEY
                )

                movieDetails = MovieDetails(
                    title = dto.title,
                    overview = dto.overview.ifEmpty { "Опис відсутній." },
                    posterUrl = "https://image.tmdb.org/t/p/w500${dto.posterPath}",
                    releaseDate = dto.releaseDate ?: "Невідомо",
                    rating = String.format("%.1f", dto.voteAverage ?: 0.0)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}