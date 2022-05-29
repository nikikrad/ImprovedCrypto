package com.example.improvedcrypto.files.presenatation.main.description.repository

import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.data.dataclass.ResponseDescription

class DescriptionRepository(
    private val coinDao: CoinDao,
    private val retrofit: ApiService
    ) {

    suspend fun getCoin(id: String): ResponseDescription? {
        return retrofit.getDescription(id).body()
    }

    fun checkCoinsFromDatabase(): List<CoinItem> {
        return coinDao.readAllData().map { it.toCoinItem() }
    }

    suspend fun sendCoinToDatabase(coinEntity: CoinEntity) {
        coinDao.addCoin(coinEntity)
    }

    suspend fun deleteCoin(coinEntity: CoinEntity) {
        coinDao.deleteCoin(coinEntity.name)
    }

}