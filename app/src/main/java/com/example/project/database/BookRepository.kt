package com.example.project.database

import androidx.lifecycle.LiveData

class BookRepository(private val bookDao: BookDao) {
    val readAllData: LiveData<List<Book>> = bookDao.getAll()

    fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun deleteAllBooks() {
        bookDao.deleteAll()
    }
}