package com.onurbarman.inviousgchallenge.data.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val Genre: String,
    val Plot: String,
    val Poster: String,
    val Title: String,
    val imdbRating: String,
    val Response: String,
    val Error: String?
) : Parcelable