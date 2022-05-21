package com.example.improvedcrypto.files.presenatation.favorite.repository

import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem

class FavoriteRepository(private val coinDao: CoinDao){



    fun readAllData(): List<CoinEntity>{
        return coinDao.readAllData()
    }

    suspend fun deleteCoin(coinEntity: CoinEntity, database: CoinDatabase?){
//        val coinDao = database?.CoinDao()
        coinDao.deleteCoin(coinEntity.name)
    }

    fun getAllData(database: CoinDatabase?): MutableList<CoinItem> {

        var coinList: MutableList<CoinItem> =
            emptyList<CoinItem>().toMutableList()
//        val coinDao = database?.CoinDao()
        val coinFromDatabase = readAllData()
        coinFromDatabase.forEach {
            coinList.add(it.toCoinItem())
        }
        return coinList
    }
}