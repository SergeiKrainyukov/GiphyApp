package com.example.giphyapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapp.data.Gif
import com.example.giphyapp.data.GiphyApiClient
import com.example.giphyapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), GifAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GifAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GifAdapter(listOf(), this)
        layoutManager = LinearLayoutManager(this)
        binding.gifRecyclerView.adapter = adapter
        binding.gifRecyclerView.layoutManager = layoutManager

        binding.btnSearch.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        hideKeyboard()

        val query = binding.searchInput.text.toString()

        GlobalScope.launch {
            val response = GiphyApiClient().searchGifs(query)

            withContext(Dispatchers.Main) {
                response?.let { adapter.update(it.data) }
            }
        }
    }

    private fun hideKeyboard() {
        val view = currentFocus ?: View(this)
        view.clearFocus()
    }

    override fun onItemClick(gif: Gif) {
        startActivity(Intent(this@MainActivity, GifDetailActivity::class.java).apply {
            putExtra(GifDetailActivity.EXTRA_GIF, gif)
        })
    }
}
