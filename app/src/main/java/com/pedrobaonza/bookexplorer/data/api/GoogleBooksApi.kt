package com.pedrobaonza.bookexplorer.data.api

import com.pedrobaonza.bookexplorer.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz que define las llamadas a la API de Google Books utilizando Retrofit.
 *
 * Retrofit genera automáticamente la implementación de esta interfaz.
 */
interface GoogleBooksApi {

    /**
     * Realiza una petición GET al endpoint de búsqueda de libros de la API de Google Books.
     *
     * URL base: https://www.googleapis.com/
     * Endpoint completo: books/v1/volumes?q={query}
     *
     * @param query Texto que se desea buscar (por ejemplo: "Harry Potter").
     * @return Un objeto [BookResponse] que contiene una lista de libros que coinciden con la búsqueda.
     *
     * La función es `suspend` porque se ejecuta dentro de una corrutina (Kotlin Coroutine),
     * lo que permite hacer llamadas asíncronas sin bloquear el hilo principal.
     */
    @GET("books/v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): BookResponse
}
