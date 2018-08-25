package org.bitbucket.moviex.data.entities

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("title")
        var title: String = "",
        @SerializedName("poster_path")
        var poster: String = "",
        @SerializedName("vote_average")
        var averageVote: Float = 0.0f
)