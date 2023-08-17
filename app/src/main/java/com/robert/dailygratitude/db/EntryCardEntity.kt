package com.robert.dailygratitude.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "entries"
)
data class EntryCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "images") val images: List<String>? = null,
    @ColumnInfo(name = "tags") val tags: List<String>? = null
)