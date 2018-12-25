package com.shohiebsense.myowntracking.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shohiebsense.myowntracking.constants.DataConstants

@Entity(
    tableName = DataConstants.TABLE_NOTE
)
data class Note(
    var title: String,
    var description: String,
    var priority : Int,
    var createdTime : String,
    var modifiedTime : String,
    var category : Category
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}