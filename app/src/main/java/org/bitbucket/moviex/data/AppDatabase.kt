package org.bitbucket.moviex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.data.local.MovieDao

/**
 * Created by Emmanuel Victor Garcia
 */

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}