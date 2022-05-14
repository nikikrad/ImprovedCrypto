package com.example.improvedcrypto.files.presenatation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    val liveData: MutableLiveData<List<CoinResponse>> = MutableLiveData()

    fun getResponse(){
        viewModelScope.launch {
                liveData.postValue(mainRepository.getCoin())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}