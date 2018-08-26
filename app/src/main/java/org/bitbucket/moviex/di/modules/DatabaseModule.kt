package org.bitbucket.moviex.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.bitbucket.moviex.App
import org.bitbucket.moviex.data.AppDatabase
import org.bitbucket.moviex.data.local.MovieDao
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java,
                "moviex-db").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

}