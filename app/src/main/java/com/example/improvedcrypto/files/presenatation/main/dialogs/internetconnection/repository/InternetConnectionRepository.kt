package com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.repository

import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance

class InternetConnectionRepository {

    suspend fun getCoin(): ArrayList<CoinResponse>? {
        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val crypto = retrofit.getCrypto()
        return crypto.body()
    }
}