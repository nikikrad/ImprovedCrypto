package com.example.improvedcrypto.files.main.description

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.repository.CoinRepository
import com.example.improvedcrypto.files.main.description.dataclass.ResponseDescription
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    suspend fun sendCoinToDatabase(coin: Coin, database: CoinDatabase?){
        val coinDao = database?.CoinDao()
        coinDao?.addCoin(coin)
    }

    override fun onCleared() {
        super.onCleared()
    }
}