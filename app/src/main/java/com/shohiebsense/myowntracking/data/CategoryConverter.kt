package com.shohiebsense.myowntracking.data

import androidx.room.TypeConverter
import com.shohiebsense.myowntracking.data.model.Category
import com.squareup.moshi.*
import com.squareup.moshi.Types.newParameterizedType
import android.R.attr.data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import java.util.Collections.emptyList






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