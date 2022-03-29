package com.example.improvedcrypto.files.presenatation.favorite

import androidx.lifecycle.ViewModel
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.presenatation.main.dataclass.toCoinEntity

class FavoriteViewModel : ViewModel() {


    fun processingCoin(coin: CoinItem): CoinEntity {
        val processedCoin = coin.toCoinEntity()
        return processedCoin
    }

    fun getAllData(database: CoinDatabase?): MutableList<CoinItem> {

        var coinList: MutableList<CoinItem> =
            emptyList<CoinItem>().toMutableList()
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { FavoriteRepository(it) }
        val coinFromDatabase = coinRepository?.readAllData

        if (coinFromDatabase != null) {
            coinFromDatabase.forEach {
                coinList.add(it.toCoinItem())
            }
        }
        return coinList
    }

    suspend fun deleteCoin(coinEntity: CoinEntity, database: CoinDatabase?){
        val coinDao = database?.CoinDao()
        coinDao?.deleteCoin(coinEntity.name)
    }
}