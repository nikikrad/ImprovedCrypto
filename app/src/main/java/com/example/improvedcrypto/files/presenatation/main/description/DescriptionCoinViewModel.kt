package com.example.improvedcrypto.files.presenatation.main.description

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.presenatation.main.description.dataclass.ResponseDescription
import com.example.improvedcrypto.files.presenatation.main.description.repository.DescriptionRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DescriptionCoinViewModel(private val descriptionRepository: DescriptionRepository) : ViewModel() {

    var liveData: MutableLiveData<ResponseDescription> = MutableLiveData()

    fun getDescriptionResponse(id: String) {

        viewModelScope.launch {
            runBlocking {
                val bodyDescription = descriptionRepository.getCoin(id)

                if (bodyDescription != null) {
                    liveData.postValue(
                        ResponseDescription(
                            bodyDescription.id,
                            bodyDescription.symbol,
                            bodyDescription.name,
                            bodyDescription.image,
                            bodyDescription.description,
                            bodyDescription.marketData
                        )
                    )
                }
            }

        }

    }

    fun getAllData(database: CoinDatabase?): MutableList<CoinItem> {

        var coinList: MutableList<CoinItem> =
            emptyList<CoinItem>().toMutableList()
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { FavoriteRepository(it) }
        val coinFromDatabase = coinRepository?.readAllData

        if (coinFromDatabase != null) {
            coinFromDatabase.forEach {
                coinList.add(
                    it.toCoinItem()
                )
            }
        }
        return coinList
    }

    fun processingDatabaseResponse(id: String?, coinList: MutableList<CoinItem>): Boolean {
        coinList.forEach {
            if (it.nameId == id) return true
        }
        return false
    }

    suspend fun sendCoinToDatabase(coinEntity: CoinEntity, database: CoinDatabase?) {
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { FavoriteRepository(it) }
        if (coinRepository != null) {
            coinRepository.addCoin(coinEntity)
        }
    }

    suspend fun deleteCoin(coinEntity: CoinEntity, database: CoinDatabase?) {
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { FavoriteRepository(it) }
        if (coinRepository != null) {
            coinRepository.deleteCoin(coinEntity)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}