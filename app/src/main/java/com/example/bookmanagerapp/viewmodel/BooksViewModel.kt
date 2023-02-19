package com.example.bookmanagerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookmanagerapp.model.BooksInfo
import com.example.bookmanagerapp.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel
@Inject
constructor(private val booksRepository: BooksRepository) : ViewModel() {

    fun insertBook(booksInfo: BooksInfo) = viewModelScope.launch {
        booksRepository.insertBook(booksInfo)
    }

    fun deleteBook(booksInfo: BooksInfo) = viewModelScope.launch {
        booksRepository.deleteBook(booksInfo)
    }
    fun updateBook(booksInfo: BooksInfo) = viewModelScope.launch {
        booksRepository.updateBook(booksInfo)
    }

    val allBooks = booksRepository.getAllBooks().asLiveData()

}
