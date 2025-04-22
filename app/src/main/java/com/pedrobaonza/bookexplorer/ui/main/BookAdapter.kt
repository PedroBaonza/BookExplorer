package com.pedrobaonza.bookexplorer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedrobaonza.bookexplorer.data.model.BookItem
import com.pedrobaonza.bookexplorer.databinding.ItemBookBinding

class BookAdapter(
    private var books: List<BookItem>,
    private val onItemClick: (BookItem) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.apply {
            tvTitle.text = book.volumeInfo.title
            tvAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "Unknown"
            tvDescription.text = book.volumeInfo.description ?: "No description"

            Glide.with(imgCover.context)
                .load(book.volumeInfo.imageLinks?.thumbnail)
                .into(imgCover)

            root.setOnClickListener {
                onItemClick(book)
            }
        }
    }

    fun updateData(newBooks: List<BookItem>) {
        books = newBooks
        notifyDataSetChanged()
    }
}