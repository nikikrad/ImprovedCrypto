package com.example.improvedcrypto.files.presenatation.main.dialogs.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DialogViewModel: ViewModel() {

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
}