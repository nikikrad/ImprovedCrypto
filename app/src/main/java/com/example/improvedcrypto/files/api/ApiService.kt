package com.example.improvedcrypto.files.api

import com.example.improvedcrypto.files.main.dataclass.CoinResponse
import com.example.improvedcrypto.files.main.description.dataclass.DescriptionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("coins/markets?vs_currency=usd")
    suspend fun getCrypto(): Response<List<CoinResponse>>

    @GET("coins/{id}")
    suspend fun getDescription(
        @Path("id") id: String
    ): Response<List<DescriptionResponse>>
}