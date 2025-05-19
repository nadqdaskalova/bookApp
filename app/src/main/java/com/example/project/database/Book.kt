package com.example.project.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val author: String
)