package com.example.improvedcrypto.files.data

import androidx.room.*


@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoin(coin: Coin)

    @Query("DELETE FROM coin_table WHERE name = :name")
    suspend fun deleteCoin(name: String)

    @Query("SELECT * FROM coin_table")
    fun readAllData(): List<Coin>

}