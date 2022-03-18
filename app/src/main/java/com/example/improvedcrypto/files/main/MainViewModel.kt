package com.example.improvedcrypto.files.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.ResponseCoinEntity
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

    suspend fun sendResponseCoinToDatabase(responseCoins: ResponseCoinEntity, database: CoinDatabase){
        val coinDao = database?.CoinDao()
        coinDao?.addResponseCoin(responseCoins)
    }

    override fun onCleared() {
        super.onCleared()
    }
}