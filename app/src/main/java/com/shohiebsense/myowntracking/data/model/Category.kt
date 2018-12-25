package com.shohiebsense.myowntracking.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    var name : String,
    var priority: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    private set
}