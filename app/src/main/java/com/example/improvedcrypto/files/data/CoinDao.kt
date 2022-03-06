package com.example.improvedcrypto.files.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoin(coin: Coin)

    @Delete
    suspend fun deleteCoin(coin: Coin)

    @Query("SELECT * FROM coin_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Coin>>
}