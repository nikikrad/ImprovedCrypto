package com.example.improvedcrypto.files.domain

import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.description.dataclass.ResponseDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.Array

interface ApiService {

    @GET("coins/markets?vs_currency=usd")
    suspend fun getCrypto(): Response<ArrayList<CoinResponse>>

    @GET("coins/{id}")
    suspend fun getDescription(
        @Path("id") id: String
    ): Response<ResponseDescription>
}