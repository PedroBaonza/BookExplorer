package com.pedrobaonza.bookexplorer.data.model

/**
 * Objeto responsable de transformar los datos obtenidos de OpenLibrary (OpenLibraryBook)
 * en objetos BookItem, que son usados por la app para mostrar la información de cada libro.
 *
 * Este mapper actúa como una capa de adaptación entre el formato externo de la API
 * y el formato interno que maneja la app.
 */
object OpenLibraryMapper {

    /**
     * Convierte una lista de libros obtenidos desde la API (OpenLibraryBook)
     * a una lista de BookItem entendibles por la app.
     *
     * @param docs Lista de libros en formato de OpenLibrary.
     * @return Lista de libros en formato BookItem.
     */
    fun mapToBookItems(docs: List<OpenLibraryBook>): List<BookItem> {
        return docs.map { book ->
            BookItem(
                id = book.key,
                volumeInfo = VolumeInfo(
                    title = book.title ?: "Sin título", // Valor por defecto si falta
                    authors = book.author_name ?: listOf("Desconocido"),
                    description = book.first_sentence?.firstOrNull() ?: "Sin descripción",
                    imageLinks = ImageLinks(
                        thumbnail = book.cover_i?.let { id ->
                            // Construye la URL de la portada a partir del ID que da OpenLibrary
                            "https://covers.openlibrary.org/b/id/$id-M.jpg"
                        }
                    )
                )
            )
        }
    }
}
