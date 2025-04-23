package com.pedrobaonza.bookexplorer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pedrobaonza.bookexplorer.databinding.ActivityDetailBinding

/**
 * Actividad que muestra los detalles de un libro seleccionado.
 *
 * Esta pantalla recibe los datos del libro a través de un Intent desde la MainActivity.
 */
class DetailActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas del layout
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Extraemos los datos enviados desde la MainActivity
        val title = intent.getStringExtra("title") ?: "Sin título"
        val author = intent.getStringExtra("author") ?: "Autor desconocido"
        val description = intent.getStringExtra("description") ?: "Sin descripción"
        val imageUrl = intent.getStringExtra("imageUrl")

        // Asignamos los datos a las vistas correspondientes
        binding.tvDetailTitle.text = title
        binding.tvDetailAuthor.text = author
        binding.tvDetailDescription.text = description

        // Cargamos la imagen usando Glide (solo si hay URL)
        Glide.with(this)
            .load(imageUrl)
            .into(binding.imgDetailCover)
    }
}
