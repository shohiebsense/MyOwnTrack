package com.shohiebsense.myowntracking.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shohiebsense.myowntracking.data.model.Breeds
import com.shohiebsense.myowntracking.data.model.Category

class BreedsConverter {

    val gson = Gson()

    @TypeConverter
    fun jsonToList(data : String) : List<Breeds>{
        val listType = object : TypeToken<List<Breeds>>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Breeds>): String {
        return gson.toJson(someObjects)
    }
}