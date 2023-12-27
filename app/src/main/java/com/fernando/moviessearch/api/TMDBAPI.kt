package com.fernando.moviessearch.api

import com.fernando.moviessearch.api.services.MovieService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object TMDBAPI {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYmM4ODk2O" +
                    "GM4MTdmMDUxN2IyOGQyNTBlZDlkZTJmNSIsInN1YiI6IjYyZDkyY2RjZDc" +
                    "wNTk0MDZiMjI2OTI1YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJz" +
                    "aW9uIjoxfQ.2emVPsgSbgUT9BbvbCpiZ3mQf9TKOqiBmsIHJnrNQ8k"
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization",
                    "Bearer $apiKey")
                .build()

            chain.proceed(request)
        }
        .build()
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val movieService = retrofit.create(MovieService::class.java)
}