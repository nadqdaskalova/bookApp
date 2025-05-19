package com.example.project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.project.database.Book
import com.example.project.database.BookViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddItem : Fragment() {

    private lateinit var etBookTitle: EditText
    private lateinit var etAuthorName: EditText
    private lateinit var mBookViewModel: BookViewModel

    private lateinit var bAddButton: Button
    private lateinit var pbAddItem: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddItem().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etBookTitle = view.findViewById(R.id.etBookTitle)
        etAuthorName = view.findViewById(R.id.etAuthorName)
        pbAddItem = view.findViewById(R.id.addProgressBar)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        bAddButton = view.findViewById(R.id.addButton)

        val textWatcher = createTextWatcher()

        etBookTitle.addTextChangedListener(textWatcher)
        etAuthorName.addTextChangedListener(textWatcher)

        bAddButton.setOnClickListener {
            setupOnClickListener()
        }
    }

    private fun setupOnClickListener() {
        val bookTitle = etBookTitle.text.toString()
        val authorName = etAuthorName.text.toString()

        if (bookTitle.isBlank()) {
            Toast.makeText(context, "Please enter the book title", Toast.LENGTH_LONG).show()
            return
        }

        if (authorName.isBlank()) {
            Toast.makeText(context, "Please enter the author's name", Toast.LENGTH_LONG).show()
            return
        }

        // Show the ProgressBar
        pbAddItem.visibility = View.VISIBLE

        // Simulate adding the book (replace this with your actual database or API call)
        view?.postDelayed({
            mBookViewModel.addBook(
                Book(null, bookTitle, authorName)
            )

            // Hide the ProgressBar
            pbAddItem.visibility = View.GONE

            // Show success message
            Toast.makeText(context, "Book added successfully", Toast.LENGTH_LONG).show()

            // Clear input fields
            etBookTitle.text.clear()
            etAuthorName.text.clear()
        }, 500) // Simulate a 2-second delay
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val targetLength = 1
                val bookTitleLength = etBookTitle.text.length
                val authorNameLength = etAuthorName.text.length

                val progress = when {
                    bookTitleLength >= targetLength && authorNameLength >= targetLength -> 100
                    bookTitleLength >= targetLength || authorNameLength >= targetLength -> 50
                    else -> 0
                }

                pbAddItem.progress = progress
            }
        }
    }
}