package org.bitbucket.moviex.di

import dagger.Component
import org.bitbucket.moviex.di.modules.AppModule
import org.bitbucket.moviex.di.modules.NetworkModule
import org.bitbucket.moviex.di.modules.ViewModelModule
import org.bitbucket.moviex.ui.movie.MovieFragment
import javax.inject.Singleton

/**
 * Created by Emmanuel Victor Garcia
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface Injector {
    fun inject(inject: MovieFragment)
}