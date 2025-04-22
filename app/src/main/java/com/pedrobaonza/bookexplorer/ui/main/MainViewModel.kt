package com.pedrobaonza.bookexplorer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrobaonza.bookexplorer.data.model.BookItem
import com.pedrobaonza.bookexplorer.data.repository.BookRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = BookRepository()

    private val _books = MutableLiveData<List<BookItem>>()
    val books: LiveData<List<BookItem>> = _books

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchBooks(query)
                _books.value = response.items ?: emptyList()
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }
}