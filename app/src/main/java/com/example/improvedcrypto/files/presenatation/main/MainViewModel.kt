package com.example.improvedcrypto.files.presenatation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val liveData: MutableLiveData<List<CoinResponse>> = MutableLiveData()

    fun getResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(mainRepository.getCoin())
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }
}