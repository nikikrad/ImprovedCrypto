package com.example.improvedcrypto.files.data

import androidx.lifecycle.LiveData
import com.example.improvedcrypto.files.data.repository.CoinRepository

class CoinRealization(private val coinDao: CoinDao): CoinRepository {

    override val readAllData: LiveData<List<Coin>>
        get() = coinDao.readAllData()

    override suspend fun insertCoin(coin: Coin, onSuccess: () -> Unit) {
        coinDao.insert(coin)
        onSuccess()
    }

    override suspend fun delete(coin: Coin, onSuccess: () -> Unit) {
        coinDao.delete(coin)
        onSuccess()
    }


}