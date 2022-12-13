package com.example.moviestest.data.models

import java.text.ParseException
import java.text.SimpleDateFormat

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    val release_year
        get(): String {

            return try {
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val d = sdf.parse(release_date)
                sdf.applyPattern("yyyy")
                sdf.format(d!!)
            } catch (e: ParseException) {
                e.printStackTrace()
                "TBA"
            }
        }
}