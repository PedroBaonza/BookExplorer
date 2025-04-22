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
 * Este adaptador se encarga de:
 * - Inflar el layout del ítem (item_book.xml).
 * - Asignar los datos de cada libro a los componentes visuales.
 * - Capturar clics en cada ítem para mostrar detalles.
 *
 * @param books Lista de objetos BookItem que se van a mostrar.
 * @param onItemClick Función lambda que se ejecuta cuando el usuario pulsa sobre un libro.
 */
class BookAdapter(
    private var books: List<BookItem>,
    private val onItemClick: (BookItem) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    /**
     * ViewHolder que contiene el binding del ítem individual (ItemBookBinding).
     */
    inner class BookViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * Infla el layout del ítem y crea una nueva instancia de ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    /**
     * Devuelve la cantidad de ítems en la lista.
     */
    override fun getItemCount(): Int = books.size

    /**
     * Asigna los datos de cada libro a los elementos visuales del ítem.
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.apply {
            // Título del libro
            tvTitle.text = book.volumeInfo.title

            // Autores, unidos por coma si hay más de uno
            tvAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "Unknown"

            // Descripción corta del libro
            tvDescription.text = book.volumeInfo.description ?: "No description"

            // Carga de imagen usando Glide
            Glide.with(imgCover.context)
                .load(book.volumeInfo.imageLinks?.thumbnail)
                .into(imgCover)

            // Listener para ir al detalle del libro
            root.setOnClickListener {
                onItemClick(book)
            }
        }
    }

    /**
     * Actualiza la lista de libros mostrados y refresca el RecyclerView.
     *
     * @param newBooks Nueva lista de libros a mostrar.
     */
    fun updateData(newBooks: List<BookItem>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
