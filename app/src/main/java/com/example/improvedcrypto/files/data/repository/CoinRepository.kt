package com.example.improvedcrypto.files.data.repository

import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.ResponseCoinEntity

class CoinRepository(private val coinDao: CoinDao){

    val readAllData: List<Coin> = coinDao.readAllData()

    suspend fun addCoin(coin: Coin){
        coinDao.addCoin(coin)
    }

    suspend fun deleteCoin(coin: Coin){
        coinDao.deleteCoin(coin.name)
    }

    val getAllCoinResponse: List<ResponseCoinEntity> = coinDao.getAllResponseCoin()

    suspend fun addResponseCoin(responseCoin: List<ResponseCoinEntity>){
        coinDao.addResponseCoin(responseCoin)
    }
}