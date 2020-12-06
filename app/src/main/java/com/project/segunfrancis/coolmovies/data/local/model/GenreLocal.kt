package com.project.segunfrancis.coolmovies.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by SegunFrancis
 */

@Entity(tableName = "genre_id_table")
data class GenreLocal(
    @PrimaryKey val id: Int,
    val name: String
)