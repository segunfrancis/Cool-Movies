package com.project.segunfrancis.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.segunfrancis.data.local.model.MovieLocal

/**
 * Created by SegunFrancis
 */

@Database(version = 1, exportSchema = false, entities = [MovieLocal::class])
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}