package com.shohiebsense.myowntracking.data.model

import com.squareup.moshi.Json

class Cat(
    @Json(name = "id") var id : Int,
    @Json(name = "url") var url: String,
    @Json(name = "categories") var category: ArrayList<Category>,
    @Json(name = "breeds") var breeds : ArrayList<Breeds>

) {

}