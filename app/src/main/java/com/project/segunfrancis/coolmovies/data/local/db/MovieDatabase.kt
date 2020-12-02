package com.project.segunfrancis.coolmovies.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.segunfrancis.coolmovies.data.local.model.MovieLocal

/**
 * Created by SegunFrancis
 */

@Database(version = 1, exportSchema = false, entities = [MovieLocal::class])
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): com.project.segunfrancis.coolmovies.data.local.db.MovieDao
}