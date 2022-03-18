package com.example.improvedcrypto.files.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.ResponseCoinEntity
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.main.dataclass.CoinResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel: ViewModel() {

    val liveData: MutableLiveData<List<CoinResponse>> = MutableLiveData()

    fun getResponse(){

        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        viewModelScope.launch {
            runBlocking {
                val getGecon = retrofit.getCrypto()
                val bodyGecon = getGecon.body()
                liveData.postValue(bodyGecon)
            }
        }
    }

    suspend fun sendResponseCoinToDatabase(responseCoins: MutableList<ResponseCoinEntity>, database: CoinDatabase){
        val coinDao = database?.CoinDao()
        coinDao?.addResponseCoin(responseCoins)
    }

    fun getAllData(database: CoinDatabase?): MutableList<CoinResponse> {

        var coinList: MutableList<CoinResponse> =
            emptyList<CoinResponse>().toMutableList()
        val coinDao = database?.CoinDao()
        val coinFromDatabase = coinDao?.getAllResponseCoin()

        if (coinFromDatabase != null) {
            coinFromDatabase.forEach {
                coinList.add(
                    CoinResponse(
                        it.nameId,
                        it.name,
                        it.symbol,
                        it.image,
                        it.price
                    )
                )
            }
        }
        return coinList
    }

    override fun onCleared() {
        super.onCleared()
    }
}