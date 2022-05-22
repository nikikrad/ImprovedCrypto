package com.example.improvedcrypto.files.presenatation.main.description.repository

import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.main.description.dataclass.ResponseDescription

class DescriptionRepository(
    private val coinDao: CoinDao,
    private val retrofit: ApiService
    ) {

    suspend fun getCoin(id: String): ResponseDescription? {
        return retrofit.getDescription(id).body()
    }

    fun checkCoinsFromDatabase(): MutableList<CoinItem> {
        val coinList: MutableList<CoinItem> = emptyList<CoinItem>().toMutableList()
        coinDao.readAllData().forEach {
            coinList.add(it.toCoinItem())
        }
        return coinList
    }

    suspend fun sendCoinToDatabase(coinEntity: CoinEntity) {
        coinDao.addCoin(coinEntity)
    }

    suspend fun deleteCoin(coinEntity: CoinEntity) {
        coinDao.deleteCoin(coinEntity.name)
    }

}