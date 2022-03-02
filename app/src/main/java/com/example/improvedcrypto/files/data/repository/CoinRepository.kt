package com.example.improvedcrypto.files.data.repository

import androidx.lifecycle.LiveData
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDao

interface CoinRepository{

    val readAllData: LiveData<List<Coin>>

    suspend fun insertCoin(coin: Coin, onSuccess:() -> Unit)
    suspend fun delete(coin: Coin, onSuccess:() -> Unit)
}