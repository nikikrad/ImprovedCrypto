package com.example.improvedcrypto.files.presenatation.favorite

import androidx.lifecycle.ViewModel
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.data.repository.CoinRepository

class FavoriteViewModel : ViewModel() {


    fun processingCoin(coin: CoinItem): CoinEntity {
        val processedCoin = CoinEntity(
            0,
            coin.nameId,
            coin.symbol,
            coin.name,
            coin.image,
            coin.description,
            coin.currentPrice,
            coin.changePrice
        )
        return processedCoin
    }

    fun getAllData(database: CoinDatabase?): MutableList<CoinItem> {

        var coinList: MutableList<CoinItem> =
            emptyList<CoinItem>().toMutableList()
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { CoinRepository(it) }
        val coinFromDatabase = coinRepository?.readAllData

        if (coinFromDatabase != null) {
            coinFromDatabase.forEach {
                coinList.add(
                    CoinItem(
                        it.nameId,
                        it.symbol,
                        it.name,
                        it.image,
                        it.description,
                        it.currentPrice,
                        it.changePrice
                    )
                )
            }
        }
        return coinList
    }

    suspend fun deleteCoin(coinEntity: CoinEntity, database: CoinDatabase?){
        val coinDao = database?.CoinDao()
        if (coinDao != null) {
            coinDao.deleteCoin(coinEntity.name)
        }
    }
}