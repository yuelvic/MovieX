package org.bitbucket.moviex.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.bitbucket.moviex.ui.movie.MovieViewModel
import org.bitbucket.moviex.utils.ViewModelFactory
import org.bitbucket.moviex.utils.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun bindMovieFragment(movieViewModel: MovieViewModel): ViewModel

}