package com.example.improvedcrypto.files.presenatation.main.description.repository

import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import com.example.improvedcrypto.files.presenatation.main.description.dataclass.ResponseDescription

class DescriptionRepository {

    suspend fun getCoin(id: String): ResponseDescription? {
        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val getDescription = retrofit.getDescription(id)
        return getDescription.body()
    }
}