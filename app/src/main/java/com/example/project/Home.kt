package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.database.Book
import com.example.project.database.BookViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var adapter: BooksAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var booksList: List<Book>

private lateinit var mBookViewModel: BookViewModel

class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.booksRv)
        recyclerView.layoutManager = layoutManager

        recyclerView.setHasFixedSize(true)

        booksList = mutableListOf()

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        dataInit()

        adapter = BooksAdapter(booksList)
        recyclerView.adapter = adapter
    }

    private fun dataInit() {
        mBookViewModel.readAllData.observe(viewLifecycleOwner, { books -> adapter.setData(books) })
    }
}