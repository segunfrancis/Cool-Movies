package com.project.segunfrancis.coolmovies.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by SegunFrancis
 */

class Converters {

    @TypeConverter
    fun listFromString(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()
        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun stringFromList(list: List<Int>): String {
        var genreIds = ""
        for (i in list)
            genreIds += ",$i"
        return genreIds
    }
}
