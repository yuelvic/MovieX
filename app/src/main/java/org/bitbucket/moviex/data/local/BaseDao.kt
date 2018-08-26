package org.bitbucket.moviex.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Created by Emmanuel Victor Garcia
 */

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}