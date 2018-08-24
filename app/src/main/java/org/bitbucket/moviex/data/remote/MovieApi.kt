package org.bitbucket.moviex.data.remote

import io.reactivex.Observable
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.entities.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Emmanuel Victor Garcia
 */

interface MovieApi {

    @GET("/trending/{media_type}/{time_window}")
    fun getTrending(
            @Query("api_key") apiKey: String,
            @Path("media_type") mediaType: String,
            @Path("time_window") timeWindow: String
    ): Observable<Result<Movie>>

}