package com.pedrobaonza.bookexplorer.data.repository

import com.pedrobaonza.bookexplorer.data.api.RetrofitInstance
import com.pedrobaonza.bookexplorer.data.model.BookResponse

class BookRepository {

    suspend fun searchBooks(query: String): BookResponse {
        return RetrofitInstance.api.searchBooks(query)
    }
}