package com.pedrobaonza.bookexplorer.data.api

import com.pedrobaonza.bookexplorer.data.model.OpenLibraryResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz que define las llamadas a la API de OpenLibrary mediante Retrofit.
 *
 * Retrofit genera automáticamente la implementación de esta interfaz en tiempo de ejecución.
 * Cada función representa un endpoint HTTP.
 */
interface GoogleBooksApi {

    /**
     * Realiza una búsqueda de libros en OpenLibrary usando el endpoint:
     * https://openlibrary.org/search.json?q={query}
     *
     * @param query Texto a buscar (título, autor, tema...).
     * @return Objeto de tipo OpenLibraryResponse con la lista de libros encontrados.
     *
     * Es una función suspendida para ejecutarse de forma asíncrona con corrutinas.
     */
    @GET("search.json")
    suspend fun searchOpenLibraryBooks(
        @Query("q") query: String
    ): OpenLibraryResponse
}
