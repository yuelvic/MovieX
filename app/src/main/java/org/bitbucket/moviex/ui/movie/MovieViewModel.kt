package org.bitbucket.moviex.ui.movie

import androidx.lifecycle.MutableLiveData
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.entities.Result
import org.bitbucket.moviex.data.repo.MovieRepository
import org.bitbucket.moviex.ui.base.BaseViewModel
import org.bitbucket.moviex.utils.extensions.Data
import org.bitbucket.moviex.utils.extensions.DataState
import org.bitbucket.moviex.utils.extensions.performOnMain
import org.jetbrains.anko.doAsync
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Emmanuel Victor Garcia
 */

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): BaseViewModel() {

    private var movieLiveData = MutableLiveData<Data<Result<Movie>>>()

    fun getPopular(page: Int): MutableLiveData<Data<Result<Movie>>> {
        Timber.d(page.toString())
        compositeDisposable.add(movieRepository.getPopular(page)
                .doOnSubscribe {
                    movieLiveData.postValue(Data(dataState = DataState.LOADING, data = null))
                }
                .performOnMain()
                .subscribe({
                    movieLiveData.postValue(Data(dataState = DataState.SUCCESS, data = it))
                    movieRepository.insertMoviesToDb(it.results)
                }, {
                    doAsync {
                        val result = Result<Movie>()
                        result.page = 1
                        result.results = movieRepository.getMoviesFromDb()
                        movieLiveData.postValue(Data(dataState = DataState.ERROR, data = result))
                    }
                })
        )
        return this.movieLiveData
    }

}