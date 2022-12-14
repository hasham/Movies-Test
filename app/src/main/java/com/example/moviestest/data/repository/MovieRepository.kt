package com.example.moviestest.data.repository


import com.example.moviestest.data.remote.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getMovies() = apiService.getMovies()
    suspend fun getMovieDetail(movie_id: Int) = apiService.getMovieDetail(movie_id = movie_id)
}