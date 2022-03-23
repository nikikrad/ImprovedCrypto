package com.example.improvedcrypto.files.main.description

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import com.example.improvedcrypto.files.main.description.dataclass.ResponseDescription
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DescriptionCoinViewModel : ViewModel() {

    var liveData: MutableLiveData<ResponseDescription> = MutableLiveData()

    fun getDescriptionResponse(id: String) {

        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        viewModelScope.launch {
            runBlocking {
                val getDescription = retrofit.getDescription(id)
                val bodyDescription = getDescription.body()

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

    fun processingDatabaseResponse(id: String?, coinList: MutableList<DatabaseParameters>): Boolean {
        coinList.forEach {
            if (it.nameId == id) return true
        }
        return false
    }

    suspend fun sendCoinToDatabase(coin: Coin, database: CoinDatabase?) {
        val coinDao = database?.CoinDao()
        val coinRepository = coinDao?.let { CoinRepository(it) }
        if (coinRepository != null) {
            coinRepository.addCoin(coin)
        }
    }

    suspend fun deleteCoin(coin: Coin, database: CoinDatabase?){
        val coinDao = database?.CoinDao()
            val coinRepository = coinDao?.let { CoinRepository(it) }
        if (coinRepository != null) {
            coinRepository.deleteCoin(coin)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}