package com.example.giphyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giphyapp.databinding.ActivityGifDetailBinding

class GifDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_GIF = "extra_gif"
    }

    private lateinit var binding: ActivityGifDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gif = intent.getParcelableExtra<Gif>(EXTRA_GIF) ?: return

//        Glide.with(this)
//            .load(gif.images.fixedWidth.url)
//            .into(binding.gifImageView)

        binding.titleTextView.text = gif.title
    }
}
