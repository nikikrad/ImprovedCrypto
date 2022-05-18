package com.example.improvedcrypto.files.presenatation.main.description.repository

import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.main.description.dataclass.ResponseDescription

class DescriptionRepository {

    suspend fun getCoin(id: String): ResponseDescription? {
        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val getDescription = retrofit.getDescription(id)
        return getDescription.body()
    }

    fun checkCoinsFromDatabase(database: CoinDatabase): MutableList<CoinItem> {
        var coinList: MutableList<CoinItem> =
            emptyList<CoinItem>().toMutableList()
        val coinDao = database.CoinDao()
        val coinFromDatabase = coinDao.readAllData()
        coinFromDatabase.forEach {
            coinList.add(
                it.toCoinItem()
            )
        }
        return coinList
    }

    suspend fun sendCoinToDatabase(coinEntity: CoinEntity, database: CoinDatabase){
        val coinDao = database.CoinDao()
        coinDao.addCoin(coinEntity)
    }

    suspend fun deleteCoin(coinEntity: CoinEntity, database: CoinDatabase) {
        val coinDao = database.CoinDao()
        coinDao.deleteCoin(coinEntity.name)
    }

}