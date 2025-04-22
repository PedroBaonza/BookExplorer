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
 * Esta pantalla permite al usuario buscar libros mediante un campo de texto.
 * Muestra los resultados en un RecyclerView, y al seleccionar un libro, abre
 * la pantalla de detalle.
 */
class MainActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas del layout de forma segura
    private lateinit var binding: ActivityMainBinding

    // ViewModel que contiene la lógica de negocio y maneja la búsqueda de libros
    private val viewModel: MainViewModel by viewModels()

    // Adaptador del RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura el RecyclerView y su adaptador
        setupRecyclerView()

        // Observa los resultados de búsqueda desde el ViewModel
        viewModel.books.observe(this) { books ->
            bookAdapter.updateData(books)
        }

        // Observa posibles errores desde el ViewModel
        viewModel.error.observe(this) { err ->
            Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
        }

        // Ejecuta la búsqueda al pulsar "enter" en el campo de texto
        binding.etSearch.setOnEditorActionListener { v, _, _ ->
            val query = v.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchBooks(query)
            }
            true
        }
    }

    /**
     * Configura el RecyclerView: inicializa el adaptador, define el layout
     * y gestiona el clic sobre un libro para abrir la pantalla de detalle.
     */
    private fun setupRecyclerView() {
        // Inicializa el adaptador con una lista vacía y una función para manejar clics
        bookAdapter = BookAdapter(emptyList()) { book ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("title", book.volumeInfo.title)
                putExtra("author", book.volumeInfo.authors?.joinToString(", "))
                putExtra("description", book.volumeInfo.description)
                putExtra("imageUrl", book.volumeInfo.imageLinks?.thumbnail)
            }
            startActivity(intent)
        }

        // Asigna el adaptador y el layout manager al RecyclerView
        binding.rvBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}
