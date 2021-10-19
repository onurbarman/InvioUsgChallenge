package com.onurbarman.inviousgchallenge.data.remote

import com.onurbarman.inviousgchallenge.data.model.movie.MovieResponse
import com.onurbarman.inviousgchallenge.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    //Get Movie
    @GET(".")
    suspend fun getMovie(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("t") title: String
    ): Response<MovieResponse>
}