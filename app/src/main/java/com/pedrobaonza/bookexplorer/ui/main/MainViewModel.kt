package com.pedrobaonza.bookexplorer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrobaonza.bookexplorer.data.model.BookItem
import com.pedrobaonza.bookexplorer.data.repository.BookRepository
import kotlinx.coroutines.launch

/**
 * ViewModel que gestiona la lógica de la pantalla principal (MainActivity).
 *
 * Es el intermediario entre la interfaz de usuario y el repositorio de datos.
 * Mantiene los datos en LiveData para que la UI los observe y se actualice automáticamente.
 */
class MainViewModel : ViewModel() {

    // Instancia del repositorio, responsable de obtener datos desde la API
    private val repository = BookRepository()

    // LiveData privado (mutable) para almacenar los resultados de búsqueda
    private val _books = MutableLiveData<List<BookItem>>()

    // LiveData público (inmutable) observado desde la UI
    val books: LiveData<List<BookItem>> = _books

    // LiveData para manejar mensajes de error
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    /**
     * Realiza una búsqueda de libros utilizando el repositorio.
     *
     * Esta función se ejecuta dentro del `viewModelScope`, que permite usar corrutinas
     * sin bloquear el hilo principal. Si la llamada es exitosa, se actualiza la lista de libros;
     * si ocurre un error, se muestra un mensaje.
     *
     * @param query Texto introducido por el usuario para buscar libros.
     */
    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchBooks(query)
                // Si la API no devuelve resultados, se usa una lista vacía
                _books.value = response.items ?: emptyList()
            } catch (e: Exception) {
                // Si ocurre un error (como fallo de red), se notifica a la UI
                _error.value = "Error: ${e.message}"
            }
        }
    }
}
