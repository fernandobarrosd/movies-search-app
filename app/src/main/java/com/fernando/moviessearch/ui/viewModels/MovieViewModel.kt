package com.fernando.moviessearch.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.moviessearch.api.TMDBAPI
import com.fernando.moviessearch.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    private val _hasError = MutableLiveData(false)

    val movie : LiveData<Movie> = _movie
    val hasError : LiveData<Boolean> = _hasError

    fun setMovie(movieID: Long) {
        val movieService = TMDBAPI.movieService

        movieService.findByID(movieID).enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    println(response.body())
                    response.body()?.let { movie -> _movie.value = movie }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                _hasError.value = true
            }

        })
    }
}