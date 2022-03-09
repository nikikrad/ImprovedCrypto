package com.example.improvedcrypto.files.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {


    fun processingCoin(coin: DatabaseParameters): Coin {
        val processedCoin = Coin(
            0,
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
        val coinFromDatabase = coinDao?.readAllData()

        if (coinFromDatabase != null) {
            coinFromDatabase.forEach {
                coinList.add(
                    DatabaseParameters(
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