package com.example.improvedcrypto.files.presenatation.main.dialogs.internet_connection.repository

import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import retrofit2.Retrofit

class InternetConnectionRepository {

    suspend fun getCoin(retrofit: ApiService): ArrayList<CoinResponse>? {
        return retrofit.getCrypto().body()
    }
}