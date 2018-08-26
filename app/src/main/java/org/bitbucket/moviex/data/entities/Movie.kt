package org.bitbucket.moviex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
        @PrimaryKey
        @SerializedName("id")
        var id: Int = 0,
        @ColumnInfo(name = "title")
        @SerializedName("title")
        var title: String = "",
        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        var poster: String = "",
        @ColumnInfo(name = "vote_average")
        @SerializedName("vote_average")
        var averageVote: Float = 0.0f
)