package com.example.imdb.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    val page: Int,

    val totalPages: Int,

    val results: List<Movie>,

    val totalResults: Int
)

data class Movie(

    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Float
)
