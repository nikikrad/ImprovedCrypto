package com.example.improvedcrypto.files.presenatation.favorite

import androidx.lifecycle.ViewModel
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import com.example.improvedcrypto.files.data.toCoinItem
import com.example.improvedcrypto.files.presenatation.main.dataclass.toCoinEntity

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val database: CoinDatabase?
) : ViewModel() {

    fun processingCoin(coin: CoinItem): CoinEntity {
        return coin.toCoinEntity()
    }

    fun getAllData(): MutableList<CoinItem> {
        return favoriteRepository.getAllData(database)
    }

    suspend fun deleteCoin(coinEntity: CoinEntity) {
        favoriteRepository.deleteCoin(coinEntity, database)
    }
}