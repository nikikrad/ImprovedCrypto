package com.example.improvedcrypto.files.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Coin::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun CoinDao(): CoinDao

    companion object {

        private var database: CoinDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CoinDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, CoinDatabase::class.java, "db").build()
                database as CoinDatabase
            }else database as CoinDatabase
        }
    }
}