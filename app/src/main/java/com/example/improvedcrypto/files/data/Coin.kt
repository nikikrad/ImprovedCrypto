package com.example.improvedcrypto.files.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coin_table")
class Coin(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var symbol: String = "",
    @ColumnInfo
    var name: String = "",
    @ColumnInfo
    var image: String = "",
    @ColumnInfo
    var description: String = "",
    @ColumnInfo
    var currentPrice: Float = 0f,
    @ColumnInfo
    var changePrice: Float = 0f
)
