package com.example.improvedcrypto.files.main.description

import android.util.Log
import androidx.lifecycle.ViewModel

class DescriptionCoinViewModel: ViewModel() {

    lateinit var id: String

    fun getCoinId(id: String){
        this.id = id
    }

    fun getDescriptionResponse(){

    }

    override fun onCleared() {
        super.onCleared()
    }
}