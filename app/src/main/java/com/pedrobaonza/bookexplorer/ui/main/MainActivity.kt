package com.pedrobaonza.bookexplorer.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrobaonza.bookexplorer.databinding.ActivityMainBinding

/**
 * Actividad principal de la aplicación.
 *
 * Permite al usuario buscar libros, ver resultados y navegar al detalle de un libro.
 */
class MainActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas de forma segura sin findViewById
    private lateinit var binding: ActivityMainBinding

    // ViewModel asociado a esta actividad. Se usa para acceder a los datos y lógica de negocio.
    private val viewModel: MainViewModel by viewModels()

    // Adaptador del RecyclerView encargado de mostrar los libros
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout XML con ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura el RecyclerView
        setupRecyclerView()

        // Observa la lista de libros en tiempo real
        viewModel.books.observe(this) { books ->
            bookAdapter.updateData(books)
        }

        // Muestra errores como mensajes Toast
        viewModel.error.observe(this) { err ->
            Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
        }

        // Ejecuta la búsqueda cuando el usuario pulsa "enter" en el campo de búsqueda
        binding.etSearch.setOnEditorActionListener { v, _, _ ->
            val query = v.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchBooks(query)
            }
            true // Indica que el evento ha sido manejado
        }
    }

    /**
     * Configura el RecyclerView: asigna el adaptador, el layout manager
     * y el comportamiento al hacer clic en un libro.
     */
    private fun setupRecyclerView() {
        // Crea el adaptador con una lambda para manejar los clics en ítems
        bookAdapter = BookAdapter(emptyList()) { book ->
            // Crea un Intent para abrir DetailActivity con los datos del libro
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("title", book.volumeInfo.title)
                putExtra("author", book.volumeInfo.authors?.joinToString(", "))
                putExtra("description", book.volumeInfo.description)
                putExtra("imageUrl", book.volumeInfo.imageLinks?.thumbnail)
            }
            startActivity(intent)
        }

        // Configura el RecyclerView con el adaptador y un layout vertical
        binding.rvBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}
