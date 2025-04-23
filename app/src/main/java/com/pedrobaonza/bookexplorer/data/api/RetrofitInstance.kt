package com.pedrobaonza.bookexplorer.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton que provee una instancia de Retrofit configurada para conectarse a la API de OpenLibrary.
 *
 * Esta clase asegura que solo exista una instancia de Retrofit en toda la aplicación.
 */
object RetrofitInstance {

    // URL base para todas las llamadas a OpenLibrary
    private const val BASE_URL = "https://openlibrary.org/"

    /**
     * Instancia del servicio API que implementa GoogleBooksApi.
     *
     * - Se inicializa de forma perezosa (lazy): solo se crea cuando se accede por primera vez.
     * - Usa Gson como convertidor de JSON a objetos Kotlin.
     */
    val api: GoogleBooksApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Establece la base de la URL
            .addConverterFactory(GsonConverterFactory.create()) // Usa Gson para el parsing de JSON
            .build()
            .create(GoogleBooksApi::class.java) // Crea la implementación de la interfaz
    }
}
