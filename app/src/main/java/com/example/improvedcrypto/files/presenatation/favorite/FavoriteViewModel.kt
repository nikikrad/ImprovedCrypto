package com.example.improvedcrypto.files.presenatation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.presenatation.main.dataclass.toCoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    val liveData: MutableLiveData<MutableList<CoinItem>> = MutableLiveData()

    fun processingCoin(coin: CoinItem): CoinEntity {
        return coin.toCoinEntity()
    }

    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(favoriteRepository.getAllData() as MutableList<CoinItem>?)
        }
    }

    suspend fun deleteCoin(coinEntity: CoinEntity) {
        favoriteRepository.deleteCoin(coinEntity)
    }
}