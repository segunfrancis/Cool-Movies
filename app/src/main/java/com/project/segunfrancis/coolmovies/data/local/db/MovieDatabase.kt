package com.project.segunfrancis.coolmovies.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.segunfrancis.coolmovies.data.local.model.GenreLocal
import com.project.segunfrancis.coolmovies.data.local.model.ResultLocal

/**
 * Created by SegunFrancis
 */

@Database(version = 1, exportSchema = false, entities = [ResultLocal::class, GenreLocal::class])
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}