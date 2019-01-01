package com.shohiebsense.myowntracking.data.model

import com.squareup.moshi.Json

data class Breeds(
    @Json(name = "id") var id : String,
    @Json(name = "name") var name : String,
    @Json(name = "temperament") var temperament : String,
    @Json(name = "life_span") var lifeSpan : String,
    @Json(name = "alt_names") var altNames : String,
    @Json(name = "wikipedia_url") var wikipediaUrl : String,
    @Json(name = "origin") var origin : String,
    @Json(name = "weight_imperial") var weightImperial : String,
    @Json(name = "experimental") var experimental : Int,
    @Json(name = "hairless") var hairless : Int,
    @Json(name = "natural") var natural : Int,
    @Json(name = "rare") var rare: Int,
    @Json(name = "rex") var rex : Int,
    @Json(name = "suppress_tail") var suppressTail : Int,
    @Json(name = "short_legs") var shortLegs : Int,
    @Json(name = "hypoallergenic") var hypoallergenic : Int,
    @Json(name = "adaptability") var adaptability : Int,
    @Json(name = "affection_level") var affectionLevel : Int,
    @Json(name = "country_code") var countryCode : String,
    @Json(name = "child_friendly") var childFriendly : Int,
    @Json(name = "dog_friendly") var dogFriendly : Int,
    @Json(name = "energy_level") var energyLevel : Int,
    @Json(name = "grooming") var grooming : Int,
    @Json(name = "health_issues") var healthIssues : Int,
    @Json(name = "intelligence") var intelligence : Int,
    @Json(name = "shedding_level") var sheddingLevel : Int,
    @Json(name = "social_needs") var socialNeeds : Int,
    @Json(name = "stranger_friendly") var strangerFriendly : Int,
    @Json(name = "vocalisation") var vocalisation : Int
) {


}