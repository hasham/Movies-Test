package com.example.moviestest.data.remote

import com.example.moviestest.BuildConfig
import com.example.moviestest.common.Constants.DISCOVER_MOVIES
import com.example.moviestest.data.models.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(DISCOVER_MOVIES)
    suspend fun getMovies(@Query("api_key") api_key: String = BuildConfig.API_KEY): MovieListResponse
}