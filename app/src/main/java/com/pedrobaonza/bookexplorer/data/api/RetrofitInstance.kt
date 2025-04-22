package com.pedrobaonza.bookexplorer.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto singleton que se encarga de crear y proveer una instancia de Retrofit configurada.
 *
 * Su propósito es centralizar la creación del cliente HTTP para conectarse con la API de Google Books.
 * Se usa el patrón singleton con `object` para que solo haya una instancia de Retrofit en toda la app.
 */
object RetrofitInstance {

    // URL base de la API de Google Books
    private const val BASE_URL = "https://www.googleapis.com/"

    /**
     * Instancia de la interfaz GoogleBooksApi.
     *
     * Se inicializa de forma perezosa (lazy), lo que significa que solo se crea cuando se accede por primera vez.
     * Retrofit se configura con:
     * - La URL base de la API
     * - Un convertidor de JSON (Gson), que convierte automáticamente las respuestas JSON a objetos Kotlin.
     */
    val api: GoogleBooksApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Define la URL base para todas las peticiones
            .addConverterFactory(GsonConverterFactory.create()) // Conversor para JSON a objetos Kotlin
            .build()
            .create(GoogleBooksApi::class.java) // Crea la implementación de la interfaz
    }
}
