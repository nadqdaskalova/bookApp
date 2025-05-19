package com.example.project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchItem : Fragment() {

    private lateinit var btnSearch: Button
    private lateinit var svSearchedBook: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_item, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchItem().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch = view.findViewById(R.id.btnSearch)
        svSearchedBook = view.findViewById(R.id.svSearchedBook)

        btnSearch.setOnClickListener {
            val desiredBookString = svSearchedBook.query.toString()

            if (desiredBookString.isNullOrBlank()) {
                Toast.makeText(context, "Please enter a book to search for", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            gotoUrl(desiredBookString)
        }
    }

    private fun gotoUrl(desiredBook: String) {
        val desiredBookUrl = desiredBook.split(" ").joinToString("+")
        val baseUrlString = "https://openlibrary.org/search?q="
        val uri: Uri = Uri.parse("$baseUrlString$desiredBookUrl&mode=everything")

        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}