package com.example.improvedcrypto.files.presenatation.favorite.repository

import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDao

class FavoriteRepository(private val coinDao: CoinDao){

    val readAllData: List<CoinEntity> = coinDao.readAllData()

    suspend fun addCoin(coinEntity: CoinEntity){
        coinDao.addCoin(coinEntity)
    }

    suspend fun deleteCoin(coinEntity: CoinEntity){
        coinDao.deleteCoin(coinEntity.name)
    }


}