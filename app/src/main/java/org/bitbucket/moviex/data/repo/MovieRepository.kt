package org.bitbucket.moviex.data.repo

import io.reactivex.Observable
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.entities.Result
import org.bitbucket.moviex.data.local.MovieDao
import org.bitbucket.moviex.data.remote.MovieApi
import org.jetbrains.anko.doAsync
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Emmanuel Victor Garcia
 */

@Singleton
class MovieRepository @Inject constructor(
        private val movieApi: MovieApi,
        private val movieDao: MovieDao
) {

    fun getTrending(
            mediaType: String,
            timeWindow: String,
            apiKey: String
    ): Observable<Result<Movie>> =
            movieApi.getTrending(mediaType, timeWindow, apiKey)
                    .doOnNext { Timber.d(it.results.toString()) }
                    .doOnError { Timber.e(it) }

    fun getMoviesFromDb(): List<Movie> {
        Timber.d(movieDao.getMovies().toString())
        return movieDao.getMovies()
    }

    fun insertMoviesToDb(movies: List<Movie>) {
        doAsync { movieDao.insert(movies) }
    }

}