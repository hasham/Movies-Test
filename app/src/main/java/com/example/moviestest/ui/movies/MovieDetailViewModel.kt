package com.example.moviestest.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviestest.common.Resource
import com.example.moviestest.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    fun getMovieDetail(movie_id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getMovieDetail(movie_id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            Timber.e(exception)
        }
    }
}