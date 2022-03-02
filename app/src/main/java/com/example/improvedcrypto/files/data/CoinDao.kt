package com.example.improvedcrypto.files.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coin: Coin)

    @Delete
    suspend fun delete(coin: Coin)

    @Query("SELECT * FROM coin_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Coin>>
}