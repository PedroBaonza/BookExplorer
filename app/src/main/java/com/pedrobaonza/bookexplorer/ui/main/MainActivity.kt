package com.pedrobaonza.bookexplorer.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrobaonza.bookexplorer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.books.observe(this) { books ->
            bookAdapter.updateData(books)
        }

        viewModel.error.observe(this) { err ->
            Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
        }

        // Buscar al pulsar "enter"
        binding.etSearch.setOnEditorActionListener { v, _, _ ->
            val query = v.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchBooks(query)
            }
            true
        }
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter(emptyList()) { book ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("title", book.volumeInfo.title)
                putExtra("author", book.volumeInfo.authors?.joinToString(", "))
                putExtra("description", book.volumeInfo.description)
                putExtra("image", book.volumeInfo.imageLinks?.thumbnail)
            }
            startActivity(intent)
        }

        binding.rvBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}