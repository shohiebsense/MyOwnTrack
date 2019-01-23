package com.shohiebsense.myowntracking.data.model

import androidx.room.*
import com.shohiebsense.myowntracking.data.CategoryConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "cats",
    indices = arrayOf(Index(value = ["id"], unique = false))
    )
@JsonClass(generateAdapter = true)
data class Cat(
    @PrimaryKey
    @Json(name = "id") var id : Int,
    @Json(name = "url") var url: String,
    @TypeConverters(CategoryConverter::class)
    @Json(name = "categories") var category: ArrayList<Category>,
    @TypeConverters(Breeds::class)
    @Json(name = "breeds") var breeds : ArrayList<Breeds>
) {


}