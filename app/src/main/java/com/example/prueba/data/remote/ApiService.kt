package com.example.prueba.data.remote

import com.example.prueba.data.model.FinalTransactionResponse
import com.example.prueba.data.model.TransactionResponse
import com.example.prueba.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("user/{name}")
    suspend fun getUserInfo(@Path("name") name: String): Response<User>

    @GET("get_transaction_id")
    suspend fun getTransactionInfo(): Response<TransactionResponse>

    @POST("confirm/{transaction_id}")
    suspend fun finishTransactionInfo(@Path("transaction_id") transaction_id: String): Response<FinalTransactionResponse>
}
