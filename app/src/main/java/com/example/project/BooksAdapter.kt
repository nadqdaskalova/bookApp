package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.database.Book

class BooksAdapter(private var booksList: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        val tvAuthorName: TextView = itemView.findViewById(R.id.tvAuthorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = booksList[position]
        holder.tvBookTitle.text = currentBook.title
        holder.tvAuthorName.text = currentBook.author
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    fun setData(newBooks: List<Book>) {
        booksList = newBooks
        notifyDataSetChanged()
    }
}