package com.pedrobaonza.bookexplorer.data.repository

import com.pedrobaonza.bookexplorer.data.api.RetrofitInstance
import com.pedrobaonza.bookexplorer.data.model.BookResponse
import com.pedrobaonza.bookexplorer.data.model.OpenLibraryMapper
import retrofit2.Response

/**
 * Repositorio responsable de obtener datos desde la API de OpenLibrary.
 *
 * Sirve como puente entre la lógica de presentación (ViewModel) y la fuente de datos remota (API).
 * Al desacoplar estas capas, se facilita el testing y la escalabilidad.
 */
class BookRepository {

    /**
     * Busca libros en la API según un texto introducido por el usuario.
     *
     * @param query Texto de búsqueda (puede ser un título, autor, etc.).
     * @return Una respuesta Retrofit con un objeto BookResponse que contiene la lista de libros.
     *
     * Esta función se ejecuta dentro de una corrutina (es suspend) para realizar la llamada de red.
     */
    suspend fun searchBooks(query: String): Response<BookResponse> {
        // Realiza la llamada a la API de OpenLibrary
        val response = RetrofitInstance.api.searchOpenLibraryBooks(query)

        // Mapea los datos crudos (OpenLibraryBook) a una lista de BookItem
        val items = OpenLibraryMapper.mapToBookItems(response.docs)

        // Envuelve la lista de BookItem dentro de un BookResponse personalizado
        return Response.success(BookResponse(items))
    }
}
