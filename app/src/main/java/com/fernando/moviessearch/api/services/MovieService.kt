package com.fernando.moviessearch.api.services

import retrofit2.Call
import com.fernando.moviessearch.models.Movie
import com.fernando.moviessearch.models.TMDBAPIResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

typealias TMDBAPIResponseMovieList = TMDBAPIResponse<List<Movie>>

interface MovieService {
    @GET("discover/movie")
    fun findAllMovies() : Call<TMDBAPIResponseMovieList>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String,
                    @Query("include_adult") includeAdult: Boolean = false) : Call<TMDBAPIResponseMovieList>

    @GET("movie/{movie_id}")
    fun findByID(@Path("movie_id") movieID: Long) : Call<Movie>
}