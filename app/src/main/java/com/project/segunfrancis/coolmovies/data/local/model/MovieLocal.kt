package com.project.segunfrancis.coolmovies.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by SegunFrancis
 */

@Entity(tableName = "movie_table")
data class MovieLocal(@PrimaryKey val id: String)