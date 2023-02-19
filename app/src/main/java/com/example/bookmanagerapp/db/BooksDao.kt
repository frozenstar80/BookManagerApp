package com.example.bookmanagerapp.db

import androidx.room.*
import com.example.bookmanagerapp.model.BooksInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(booksInfo: BooksInfo)

    @Delete
    suspend fun deleteBook(booksInfo: BooksInfo)

    @Query("SELECT * FROM todo ORDER BY Name ASC ")
    fun getAllBooks(): Flow<List<BooksInfo>>

    @Update
    suspend fun update(booksInfo: BooksInfo)


}