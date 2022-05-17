package com.example.improvedcrypto.files.presenatation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.InternetConnectionDialogFragment
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

    override fun onCleared() {
        super.onCleared()
    }
}