package com.example.prueba.data.remote

import com.example.prueba.data.model.BINResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface BINService {
    @Headers(
        "X-RapidAPI-Key: f63b74889cmsh2fa0961ab072d5ap137537jsnfa4cba4446cd",
        "X-RapidAPI-Host: bin-ip-checker.p.rapidapi.com",
    )
    @POST("/")
    suspend fun checkBin(@Query("bin") bin: String): Response<BINResponse>
}
