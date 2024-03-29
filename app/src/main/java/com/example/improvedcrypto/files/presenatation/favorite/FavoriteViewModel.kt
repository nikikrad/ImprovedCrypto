package com.example.improvedcrypto.files.presenatation.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    val listCoinItem: MutableLiveData<MutableList<CoinItem>> = MutableLiveData()

    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            listCoinItem.postValue(favoriteRepository.getAllData() as MutableList<CoinItem>?)
        }
    }

    suspend fun deleteCoin(coinEntity: CoinEntity) {
        favoriteRepository.deleteCoin(coinEntity)
    }
}