package com.pedrobaonza.bookexplorer.data.model

/**
 * Modelo principal que representa la respuesta completa de la API de Google Books.
 *
 * La API devuelve un objeto JSON con una lista de libros bajo la clave "items".
 */
data class BookResponse(
    val items: List<BookItem>? // Lista de libros encontrados (puede ser null si no hay resultados)
)

/**
 * Representa cada libro individual dentro de la lista de resultados.
 */
data class BookItem(
    val id: String,             // ID único del libro (puede ser usado como clave)
    val volumeInfo: VolumeInfo  // Información detallada del libro (título, autor, etc.)
)

/**
 * Información detallada de un libro (subobjeto dentro del resultado JSON).
 */
data class VolumeInfo(
    val title: String,          // Título del libro
    val authors: List<String>?, // Lista de autores (puede ser null si no hay datos)
    val description: String?,   // Descripción del contenido del libro (puede ser null)
    val imageLinks: ImageLinks? // Objeto que contiene las URLs de las imágenes del libro
)

/**
 * Contiene los enlaces a las imágenes del libro.
 */
data class ImageLinks(
    val thumbnail: String?      // URL de la miniatura de la portada del libro
)
