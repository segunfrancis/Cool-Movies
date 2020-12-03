package com.project.segunfrancis.coolmovies.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.segunfrancis.coolmovies.data.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(result: Result)

    @Query("DELETE FROM movie_table WHERE :id is id")
    suspend fun removeFavorite(id: Int)

    @Query("SELECT * FROM movie_table")
    fun getAllFavorites(): Flow<List<Result>>
}