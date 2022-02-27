package com.example.improvedcrypto.files.main.description

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.api.ApiService
import com.example.improvedcrypto.files.api.instance.RetrofitInstance
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

    override fun onCleared() {
        super.onCleared()
    }
}