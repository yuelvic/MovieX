package org.bitbucket.moviex.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Emmanuel Victor Garcia
 */

data class Result<T>(
        @SerializedName("page")
        var page: Int = 0,
        @SerializedName("results")
        var results: List<T> = emptyList()
)