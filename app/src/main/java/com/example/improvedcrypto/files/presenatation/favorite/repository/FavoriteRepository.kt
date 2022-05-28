package com.example.improvedcrypto.files.presenatation.favorite.repository

import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem

class FavoriteRepository(private val coinDao: CoinDao){

    suspend fun deleteCoin(coinEntity: CoinEntity){
        coinDao.deleteCoin(coinEntity.name)
    }

    fun getAllData(): List<CoinItem> {
        return coinDao.readAllData().map { it.toCoinItem() }
    }
}