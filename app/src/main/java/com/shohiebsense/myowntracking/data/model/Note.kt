package com.shohiebsense.myowntracking.data.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.shohiebsense.myowntracking.utils.constants.DataConstants

@Entity(
    tableName = DataConstants.TABLE_NOTE,
    foreignKeys = [ForeignKey(entity = Category::class, parentColumns = [DataConstants.ATTRIBUTE.id], childColumns = [DataConstants.ATTRIBUTE.categoryId])],
    indices = [Index(DataConstants.ATTRIBUTE.categoryId)]
)
data class Note(
    var title: String,
    var description: String,
    var priority : Int,
    var createdTime : String,
    var modifiedTime : String,
    var categoryId : String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}