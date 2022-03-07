package com.example.improvedcrypto.files.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {


    fun processingCoin(coin: DatabaseParameters): Coin{
        val processedCoin = Coin(0, coin.symbol, coin.name, coin.image, coin.description, coin.currentPrice, coin.changePrice)
        return processedCoin
    }


}