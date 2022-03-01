package com.example.improvedcrypto.files.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coin_table")
data class Coin(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var symbol: String,
    var name: String,
    var image: String,
    var description: String,
    var currentPrice: Float,
    var changePrice: Float
)
