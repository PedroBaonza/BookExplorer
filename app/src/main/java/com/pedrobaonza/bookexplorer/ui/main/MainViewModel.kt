package com.pedrobaonza.bookexplorer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrobaonza.bookexplorer.data.model.BookItem
import com.pedrobaonza.bookexplorer.data.repository.BookRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel que gestiona la lógica de la pantalla principal (MainActivity).
 *
 * Se encarga de:
 * - Ejecutar la búsqueda de libros mediante el repositorio.
 * - Exponer los resultados a la UI usando LiveData.
 * - Manejar errores y mostrarlos a la UI.
 */
class MainViewModel : ViewModel() {

    // Repositorio que contiene la lógica para acceder a la API
    private val repository = BookRepository()

    // LiveData privado (modificable internamente) para la lista de libros encontrados
    private val _books = MutableLiveData<List<BookItem>>()

    // LiveData público (solo lectura) para que la UI observe los resultados
    val books: LiveData<List<BookItem>> = _books

    // LiveData para mostrar errores en la UI
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Job que se usa para cancelar búsquedas anteriores si el usuario escribe rápidamente
    private var searchJob: Job? = null

    /**
     * Busca libros en la API según el texto introducido por el usuario.
     *
     * Esta función cancela búsquedas anteriores si el usuario escribe demasiado rápido (debounce).
     * La búsqueda se ejecuta en una corrutina dentro del `viewModelScope`.
     *
     * @param query Texto de búsqueda (título, autor, etc.)
     */
    fun searchBooks(query: String) {
        // Cancela la búsqueda anterior si todavía está activa
        searchJob?.cancel()

        // Lanza una nueva búsqueda tras un breve retraso (debounce)
        searchJob = viewModelScope.launch {
            delay(600) // Espera para evitar hacer muchas llamadas seguidas (optimiza)

            try {
                // Llama al repositorio para hacer la búsqueda
                val response = repository.searchBooks(query)

                // Si la respuesta fue exitosa, actualiza la lista de libros
                if (response.isSuccessful) {
                    _books.value = response.body()?.items ?: emptyList()
                } else {
                    // Manejo de errores según el código HTTP
                    _error.value = when (response.code()) {
                        429 -> "Has hecho demasiadas búsquedas. Espera un poco." // Rate limit
                        else -> "Error ${response.code()}: ${response.message()}"
                    }
                }
            } catch (e: Exception) {
                // Errores inesperados como falta de conexión
                _error.value = "Error inesperado: ${e.message}"
            }
        }
    }
}
