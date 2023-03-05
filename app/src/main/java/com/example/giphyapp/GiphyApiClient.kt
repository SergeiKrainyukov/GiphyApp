package com.example.giphyapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyApiClient {
    companion object {
        private const val BASE_URL = "https://api.giphy.com/"
        private const val API_KEY = "IIyH900ammP9yBQRX77hzoR2UdAu9ThD"
        private const val LIMIT = 25
        private const val OFFSET = 0
    }

    private val service: GiphyApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GiphyApi::class.java)
    }

    suspend fun searchGifs(query: String): GiphyResponse? {
        val response = service.searchGifs(API_KEY, query, LIMIT, OFFSET)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}
