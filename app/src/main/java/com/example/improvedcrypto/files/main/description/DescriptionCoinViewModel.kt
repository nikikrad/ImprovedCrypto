package com.example.improvedcrypto.files.main.description

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
import com.example.improvedcrypto.files.main.description.dataclass.DescriptionCoinData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DescriptionCoinViewModel: ViewModel() {

    private lateinit var id: String
    var liveData: MutableLiveData<List<DescriptionCoinData>> = MutableLiveData()

    fun getCoinId(id: String){
        this.id = id
    }

    fun getDescriptionResponse(){

        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        viewModelScope.launch {
            runBlocking {
                val getDescription = retrofit.getDescription(id)
                val bodyDescription = getDescription.body()
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}