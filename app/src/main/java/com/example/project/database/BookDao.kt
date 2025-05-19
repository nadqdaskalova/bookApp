package com.example.project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun addBook(book: Book)

    // Order in descending order to get the newest first
    @Query("SELECT * FROM book_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Book>>

    @Query("DELETE FROM book_table")
    fun deleteAll()
}