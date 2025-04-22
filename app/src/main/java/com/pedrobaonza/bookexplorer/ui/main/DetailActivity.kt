package com.pedrobaonza.bookexplorer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pedrobaonza.bookexplorer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title") ?: ""
        val author = intent.getStringExtra("author") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val imageUrl = intent.getStringExtra("imageUrl")

        binding.tvDetailTitle.text = title
        binding.tvDetailAuthor.text = author
        binding.tvDetailDescription.text = description

        Glide.with(this)
            .load(imageUrl)
            .into(binding.imgDetailCover)
    }
}
