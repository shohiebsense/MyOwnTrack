package com.shohiebsense.myowntracking.data

import androidx.room.TypeConverter
import com.shohiebsense.myowntracking.model.Category
import com.squareup.moshi.*
import com.google.gson.Gson


class CategoryConverter {

    val gson = Gson()
    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun jsonToList(data : String) : List<Category>{
      /*  val listType = object : TypeToken<List<Category>>(){}.type
        return gson.fromJson(data, listType)*/
        val type = Types.newParameterizedType(List::class.java, Category::class.java)
        val adapter = moshi.adapter<List<Category>>(type)
        return adapter.fromJson(data)!!
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Category>): String {
        //return gson.toJson(someObjects)
        val type = Types.newParameterizedType(List::class.java, Category::class.java)
        val adapter = moshi.adapter<List<Category>>(type)
        return adapter.toJson(someObjects)!!
    }





}