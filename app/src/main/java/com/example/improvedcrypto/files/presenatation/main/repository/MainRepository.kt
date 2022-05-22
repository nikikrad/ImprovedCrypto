package com.example.improvedcrypto.files.presenatation.main.repository

import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import retrofit2.Response

class MainRepository(private val retrofit: ApiService) {

    suspend fun getCoin(): List<CoinResponse>? {
        return retrofit.getCrypto().body()
    }
}