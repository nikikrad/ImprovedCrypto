package com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class InternetConnectionDialogViewModel : ViewModel() {

    val liveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    fun getResponse() {

        val retrofit = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        viewModelScope.launch {
            try {
                val getGecon = retrofit.getCrypto()
                val bodyGecon = getGecon.body()
                if (bodyGecon != null)
                    liveData.postValue(true)
            } catch (e: Exception) {
                liveData.postValue(false)
            }

        }

    }
}