package com.example.improvedcrypto.files.favorite

import androidx.lifecycle.ViewModel
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository

class FavoriteViewModel : ViewModel() {


    fun processingCoin(coin: DatabaseParameters): Coin {
        val processedCoin = Coin(
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

    fun getAllData(database: CoinDatabase?): MutableList<DatabaseParameters> {

        var coinList: MutableList<DatabaseParameters> =
            emptyList<DatabaseParameters>().toMutableList()
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { CoinRepository(it) }
        val coinFromDatabase = coinRepository?.readAllData

        if (coinFromDatabase != null) {
            coinFromDatabase.forEach {
                coinList.add(
                    DatabaseParameters(
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

    suspend fun deleteCoin(coin: Coin, database: CoinDatabase?){
        val coinDao = database?.CoinDao()
        if (coinDao != null) {
            coinDao.deleteCoin(coin.name)
        }
    }
}