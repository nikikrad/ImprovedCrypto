package com.example.improvedcrypto.files.data.repository

import androidx.lifecycle.LiveData
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDao

class CoinRepository(private val coinDao: CoinDao) {

    val readAllData: LiveData<List<Coin>> = coinDao.readAllData()

    suspend fun addCoin (coin: Coin){
        coinDao.addCoin(coin)
    }
}