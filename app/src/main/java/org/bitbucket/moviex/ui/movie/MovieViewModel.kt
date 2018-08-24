package org.bitbucket.moviex.ui.movie

import androidx.lifecycle.MutableLiveData
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.entities.Result
import org.bitbucket.moviex.data.repo.MovieRepository
import org.bitbucket.moviex.ui.base.BaseViewModel
import org.bitbucket.moviex.utils.extensions.Data
import org.bitbucket.moviex.utils.extensions.DataState
import org.bitbucket.moviex.utils.extensions.performOnMain
import javax.inject.Inject

/**
 * Created by Emmanuel Victor Garcia
 */

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): BaseViewModel() {

    private var movieLiveData = MutableLiveData<Data<Result<Movie>>>()

    fun getTrending(
            apiKey: String,
            mediaType: String,
            timeWindow: String
    ): MutableLiveData<Data<Result<Movie>>> {
        compositeDisposable.add(movieRepository.getTrending(apiKey, mediaType, timeWindow)
                .doOnSubscribe {
                    movieLiveData.postValue(Data(dataState = DataState.LOADING, data = movieLiveData.value?.data))
                }
                .performOnMain()
                .subscribe({
                    movieLiveData.postValue(Data(dataState = DataState.SUCCESS, data = it))
                }, {
                    movieLiveData.postValue(Data(dataState = DataState.ERROR, data = movieLiveData.value?.data))
                })
        )
        return this.movieLiveData
    }

}