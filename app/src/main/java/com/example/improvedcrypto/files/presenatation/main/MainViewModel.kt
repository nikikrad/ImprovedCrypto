package com.example.improvedcrypto.files.presenatation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val liveData: MutableLiveData<List<CoinResponse>> = MutableLiveData()
    val liveDataBoolean: MutableLiveData<Boolean> = MutableLiveData()

    fun getResponse() {
        viewModelScope.launch {
            try {
                liveData.postValue(mainRepository.getCoin())
                liveDataBoolean.postValue(false)
            }catch (e: Exception){
                liveDataBoolean.postValue(true)
            }
        }
    }
}