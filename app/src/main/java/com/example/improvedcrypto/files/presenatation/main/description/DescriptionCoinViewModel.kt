package com.example.improvedcrypto.files.presenatation.main.description

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.data.dataclass.ResponseDescription
import com.example.improvedcrypto.files.data.dataclass.toResponseDescription
import com.example.improvedcrypto.files.presenatation.main.description.repository.DescriptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DescriptionCoinViewModel(
    private val descriptionRepository: DescriptionRepository
    ) :ViewModel() {

    var responseCoin: MutableLiveData<ResponseDescription> = MutableLiveData()
    var listCoinItem: MutableLiveData<MutableList<CoinItem>> = MutableLiveData()

    fun getDescriptionResponse(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bodyDescription = descriptionRepository.getCoin(id)
                if (bodyDescription != null) {
                    responseCoin.postValue(bodyDescription.toResponseDescription())
                }
            } catch (e: Exception) {
            }
        }
    }

    fun getAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            listCoinItem.postValue(descriptionRepository.checkCoinsFromDatabase() as MutableList<CoinItem>?)
        }
    }

    fun processingDatabaseResponse(id: String?, coinList: MutableList<CoinItem>): Boolean {
        coinList.forEach {
            if (it.nameId == id) return true
        }
        return false
    }

    suspend fun sendCoinToDatabase(coinEntity: CoinEntity) {
        descriptionRepository.sendCoinToDatabase(coinEntity)
    }

    suspend fun deleteCoin(coinEntity: CoinEntity) {
        descriptionRepository.deleteCoin(coinEntity)
    }
}