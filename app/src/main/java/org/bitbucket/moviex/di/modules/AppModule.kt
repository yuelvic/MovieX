package org.bitbucket.moviex.di.modules

import dagger.Module
import dagger.Provides
import org.bitbucket.moviex.App
import javax.inject.Singleton

/**
 * Created by Emmanuel Victor Garcia
 */

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideApp(): App = app
}