package com.onurbarman.inviousgchallenge.data.repository

import com.onurbarman.inviousgchallenge.data.model.movie.MovieResponse
import com.onurbarman.inviousgchallenge.data.remote.Resource
import com.onurbarman.inviousgchallenge.data.remote.ServiceClientInstance
import com.onurbarman.inviousgchallenge.utils.Utils.safeApiCall

class InvioUsgRepository {

    suspend fun getMovie(title: String): Resource<MovieResponse> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getMovie(title = title) })
    }
}