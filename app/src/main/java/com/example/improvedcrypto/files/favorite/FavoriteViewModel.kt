package com.example.improvedcrypto.files.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {

    private val readAllData: LiveData<List<Coin>>
    private val repository: CoinRepository

    init{
        val coinDao = CoinDatabase.getDatabase().coinDao()
        repository = CoinRepository(coinDao)
        readAllData = repository.readAllData
    }

    fun addCoin(coin: Coin){
        viewModelScope.launch(Dispatchers.IO){
         repository.addCoin(coin)
        }
    }

}