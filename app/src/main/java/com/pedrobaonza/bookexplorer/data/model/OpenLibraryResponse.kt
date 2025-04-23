package com.pedrobaonza.bookexplorer.data.model

data class OpenLibraryResponse(
    val docs: List<OpenLibraryBook>
)

data class OpenLibraryBook(
    val key: String,                     // e.g., "/works/OL82563W"
    val title: String?,
    val author_name: List<String>?,
    val first_sentence: List<String>?,   // Usaremos como descripción opcional
    val cover_i: Int?                    // ID de la portada
)
