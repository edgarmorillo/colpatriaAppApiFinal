package com.example.prueba.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.mocki.io/v2/0ms8mu9j/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    private const val BASE_URL_2 = "https://bin-ip-checker.p.rapidapi.com/"
    val binCheckerService: BINService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BINService::class.java)
    }
}
