package com.example.giphyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        // set up RecyclerView
        adapter = GifAdapter(listOf(), this)
        layoutManager = LinearLayoutManager(this)
        binding.gifRecyclerView.adapter = adapter
        binding.gifRecyclerView.layoutManager = layoutManager

        // set up search button click listener
        binding.btnSearch.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        // hide keyboard
        hideKeyboard()

        // get search query from EditText
        val query = binding.searchInput.text.toString()

        // make API request
        GlobalScope.launch {
            val response = GiphyApiClient().searchGifs(query)

            withContext(Dispatchers.Main) {
                // update RecyclerView with new data
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
