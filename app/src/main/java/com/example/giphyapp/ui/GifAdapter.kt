package com.example.giphyapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapp.data.Gif
import com.example.giphyapp.databinding.ItemGifBinding

class GifAdapter(private var gifs: List<Gif>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = gifs[position]
        holder.bind(gif)
    }

    override fun getItemCount(): Int = gifs.size

    inner class GifViewHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var gif: Gif

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(gif: Gif) {
            this.gif = gif
            binding.titleTextView.text = gif.title
            Glide.with(binding.root).load(gif.images.fixedWidth.url).into(binding.gifImageView)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(gif)
        }
    }

    fun update(gifs: List<Gif>){
        this.gifs = gifs
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(gif: Gif)
    }
}

