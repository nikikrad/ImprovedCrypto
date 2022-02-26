package com.example.improvedcrypto.files.main.description

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
import com.example.improvedcrypto.files.main.description.dataclass.DescriptionResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DescriptionCoinViewModel: ViewModel() {

    private lateinit var id: String
    var liveData: MutableLiveData<DescriptionResponse> = MutableLiveData()
    lateinit var infoCoin: DescriptionResponse

    fun getCoinId(id: String){
        this.id = id
    }

    fun getDescriptionResponse(){

        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        viewModelScope.launch {
            runBlocking {
                val getDescription = retrofit.getDescription(id)
                val bodyDescription = getDescription.body()
                val getGecon = retrofit.getCrypto()
                val bodyGecon = getGecon.body()
//                infoCoin.id =
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}