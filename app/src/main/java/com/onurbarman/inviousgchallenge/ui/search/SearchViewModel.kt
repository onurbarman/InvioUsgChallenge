package com.onurbarman.inviousgchallenge.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurbarman.inviousgchallenge.data.custom.SingleLiveEvent
import com.onurbarman.inviousgchallenge.data.model.movie.MovieResponse
import com.onurbarman.inviousgchallenge.data.repository.InvioUsgRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: InvioUsgRepository
): ViewModel() {

    val postMovie: SingleLiveEvent<MovieResponse> by lazy {
        SingleLiveEvent()
    }

    fun getMovie(title: String) {
        viewModelScope.launch {
            val retrofitPost = repository.getMovie(title)
            retrofitPost.data?.let {
                postMovie.postValue(retrofitPost.data)
            }
        }
    }
}