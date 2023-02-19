package com.example.bookmanagerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class BooksInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Name: String,
    val Number:String,
    val BookName:String
)
