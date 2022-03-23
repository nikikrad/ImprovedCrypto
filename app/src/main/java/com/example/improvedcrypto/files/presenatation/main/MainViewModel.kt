package com.example.improvedcrypto.files.presenatation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
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

    override fun onCleared() {
        super.onCleared()
    }
}