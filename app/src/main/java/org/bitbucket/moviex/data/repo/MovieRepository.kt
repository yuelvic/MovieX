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
            timeWindow: String
    ): Observable<Result<Movie>> =
            movieApi.getTrending(mediaType, timeWindow)
                    .doOnNext { Timber.d(it.results.toString()) }
                    .doOnError { Timber.e(it) }

    fun getPopular(page: Int): Observable<Result<Movie>> =
        movieApi.getPopular(page)
                .doOnNext { Timber.d(it.results.toString()) }
                .doOnError { Timber.e(it) }

    fun getMoviesFromDb(): List<Movie> = movieDao.getMovies()

    fun getMoviesFromDb(query: String): List<Movie> = movieDao.getMovies(query)

    fun insertMoviesToDb(movies: List<Movie>) {
        doAsync { movieDao.insert(movies) }
    }

}