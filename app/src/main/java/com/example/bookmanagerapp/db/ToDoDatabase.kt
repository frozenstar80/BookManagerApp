package com.example.bookmanagerapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookmanagerapp.model.BooksInfo

@Database(
    entities = [BooksInfo::class],
    version = 1, exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
//Extend Room Database
    abstract fun booksDao(): BooksDao

}