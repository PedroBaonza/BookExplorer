package com.pedrobaonza.bookexplorer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pedrobaonza.bookexplorer.databinding.ActivityDetailBinding

/**
 * Actividad encargada de mostrar la información detallada de un libro seleccionado.
 *
 * Esta pantalla se abre al pulsar un ítem en el RecyclerView de la pantalla principal.
 * Recibe los datos del libro a través del Intent.
 */
class DetailActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas del layout activity_detail.xml
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos los datos enviados desde MainActivity
        val title = intent.getStringExtra("title") ?: "" // Título del libro
        val author = intent.getStringExtra("author") ?: "" // Autor o autores
        val description = intent.getStringExtra("description") ?: "" // Descripción
        val imageUrl = intent.getStringExtra("imageUrl") // URL de la portada

        // Asignamos los datos a las vistas correspondientes
        binding.tvDetailTitle.text = title
        binding.tvDetailAuthor.text = author
        binding.tvDetailDescription.text = description

        // Cargamos la imagen de la portada usando Glide (si existe)
        Glide.with(this)
            .load(imageUrl)
            .into(binding.imgDetailCover)
    }
}
