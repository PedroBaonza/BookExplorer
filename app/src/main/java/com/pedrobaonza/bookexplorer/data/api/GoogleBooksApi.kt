package com.pedrobaonza.bookexplorer.data.api

import com.pedrobaonza.bookexplorer.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("books/v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): BookResponse
}