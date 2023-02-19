package com.example.bookmanagerapp.utils

import com.example.bookmanagerapp.model.BooksInfo

interface DeleteBook {
    fun onDelete(booksInfo: BooksInfo)
}