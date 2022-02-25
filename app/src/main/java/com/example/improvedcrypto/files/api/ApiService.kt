package com.example.improvedcrypto.files.api

import com.example.improvedcrypto.files.main.dataclass.CoinResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("coins/markets?vs_currency=usd")
    suspend fun getCrypto(): Response<List<CoinResponse>>
}