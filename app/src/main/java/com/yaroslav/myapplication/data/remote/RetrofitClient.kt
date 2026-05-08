package com.yaroslav.myapplication.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val apiService: MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
            .create(MovieApiService::class.java)
    }
}