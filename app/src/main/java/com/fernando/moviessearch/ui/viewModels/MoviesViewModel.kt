package com.fernando.moviessearch.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.moviessearch.api.TMDBAPI
import com.fernando.moviessearch.api.services.TMDBAPIResponseMovieList
import com.fernando.moviessearch.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
    private val _allMovies = MutableLiveData<List<Movie>>()
    private val _hasError = MutableLiveData(false)
    private val _movieService = TMDBAPI.movieService


    val allMovies : LiveData<List<Movie>> = _allMovies
    val hasError : LiveData<Boolean> = _hasError

    fun setMovies() {
        _movieService.findAllMovies().enqueue(object: Callback<TMDBAPIResponseMovieList>{
            override fun onResponse(
                call: Call<TMDBAPIResponseMovieList>,
                response: Response<TMDBAPIResponseMovieList>) {
                if (response.isSuccessful) {
                    response.body()?.let { updateMovies(it.results) }
                }
            }

            override fun onFailure(call: Call<TMDBAPIResponseMovieList>, t: Throwable) {
                _hasError.value = true
            }

        })
    }

    fun searchMovies(query: String) {
        _movieService.searchMovies(query)
            .enqueue(object: Callback<TMDBAPIResponseMovieList>{
                override fun onResponse(
                    call: Call<TMDBAPIResponseMovieList>,
                    response: Response<TMDBAPIResponseMovieList>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val movies = it.results

                            if (movies.isNotEmpty()) {
                                println(movies != _allMovies.value)
                                if (movies != _allMovies.value) {
                                    updateMovies(movies)
                                }
                                else {
                                    setMovies()
                                }
                            }
                            else {
                                setMovies()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<TMDBAPIResponseMovieList>, t: Throwable) {
                   setMovies()
                }

            })
    }

    private fun updateMovies(movies: List<Movie>) {
        _allMovies.value = movies
    }


}