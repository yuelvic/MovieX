package org.bitbucket.moviex.data.repo

import io.reactivex.Observable
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.entities.Result
import org.bitbucket.moviex.data.remote.MovieApi
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Emmanuel Victor Garcia
 */

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    fun getTrending(
            apiKey: String,
            mediaType: String,
            timeWindow: String
    ): Observable<Result<Movie>> =
            movieApi.getTrending(apiKey, mediaType, timeWindow)
                    .doOnNext { Timber.d(it.results.toString()) }
                    .doOnError { Timber.e(it) }

}