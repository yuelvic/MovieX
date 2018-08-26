package org.bitbucket.moviex.data.remote

import io.reactivex.Observable
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.entities.Result
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Emmanuel Victor Garcia
 */

interface MovieApi {

    @GET("trending/{media_type}/{time_window}")
    fun getTrending(
            @Path("media_type") mediaType: String,
            @Path("time_window") timeWindow: String
    ): Observable<Result<Movie>>

    @GET("movie/popular")
    fun getPopular(): Observable<Result<Movie>>

}