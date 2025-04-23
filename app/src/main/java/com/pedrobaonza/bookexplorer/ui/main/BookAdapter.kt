package com.pedrobaonza.bookexplorer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedrobaonza.bookexplorer.data.model.BookItem
import com.pedrobaonza.bookexplorer.databinding.ItemBookBinding

/**
 * Adaptador personalizado para mostrar una lista de libros en un RecyclerView.
 *
 * Se encarga de:
 * - Inflar el layout del ítem (item_book.xml).
 * - Asignar datos del libro a los elementos visuales.
 * - Detectar clics sobre cada libro para navegar a los detalles.
 *
 * @param books Lista de libros a mostrar.
 * @param onItemClick Acción que se ejecuta al pulsar un libro.
 */
class BookAdapter(
    private var books: List<BookItem>,
    private val onItemClick: (BookItem) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    /**
     * ViewHolder que representa cada ítem del RecyclerView.
     * Contiene el binding de la vista de ítem.
     */
    inner class BookViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * Crea un nuevo ViewHolder inflando el layout del ítem.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    /**
     * Devuelve la cantidad total de libros en la lista.
     */
    override fun getItemCount(): Int = books.size

    /**
     * Asigna los datos de un libro a su vista correspondiente.
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.apply {
            // Título
            tvTitle.text = book.volumeInfo.title

            // Autores (si hay más de uno, se separan por comas)
            tvAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "Autor desconocido"

            // Descripción breve
            tvDescription.text = book.volumeInfo.description ?: "Sin descripción"

            // Carga de imagen de portada (si existe)
            Glide.with(imgCover.context)
                .load(book.volumeInfo.imageLinks?.thumbnail)
                .into(imgCover)

            // Acción al hacer clic: lanza la función que viene por parámetro
            root.setOnClickListener {
                onItemClick(book)
            }
        }
    }

    /**
     * Actualiza la lista de libros y notifica al RecyclerView para redibujar.
     *
     * @param newBooks Nueva lista de libros.
     */
    fun updateData(newBooks: List<BookItem>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
