package org.bitbucket.moviex.data.local

import androidx.room.Dao
import androidx.room.Query
import org.bitbucket.moviex.data.entities.Movie

@Dao
interface MovieDao: BaseDao<Movie> {

    @Query("SELECT * FROM movie")
    fun getMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :query")
    fun getMovies(query: String): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Movie

}