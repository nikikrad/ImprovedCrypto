package com.example.improvedcrypto.files.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoin(coin: Coin)

    @Query("SELECT * FROM coin_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Coin>>
}