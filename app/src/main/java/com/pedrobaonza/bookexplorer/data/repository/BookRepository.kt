package com.pedrobaonza.bookexplorer.data.repository

import com.pedrobaonza.bookexplorer.data.api.RetrofitInstance
import com.pedrobaonza.bookexplorer.data.model.BookResponse

/**
 * Repositorio encargado de manejar la lógica de acceso a datos desde la API de Google Books.
 *
 * Su función es abstraer la fuente de datos (en este caso, la API remota) y proporcionar los datos
 * a otras capas de la aplicación, como el ViewModel.
 *
 * Este enfoque permite desacoplar el origen de los datos del resto de la app y facilita el testing.
 */
class BookRepository {

    /**
     * Busca libros según una cadena de texto enviada por el usuario.
     *
     * @param query Texto de búsqueda (puede ser título, autor, tema, etc.).
     * @return Un objeto [BookResponse] que contiene una lista de libros encontrados.
     *
     * Esta función es `suspend` porque realiza una llamada de red usando Retrofit
     * y debe ejecutarse dentro de una corrutina para no bloquear el hilo principal.
     */
    suspend fun searchBooks(query: String): BookResponse {
        return RetrofitInstance.api.searchBooks(query)
    }
}
