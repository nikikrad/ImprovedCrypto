package com.example.improvedcrypto.files.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "response_coin_table", indices = [Index(value = ["name"], unique = true)])
class ResponseCoinEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var nameId: String = "",
    @ColumnInfo
    var name: String = "",
    @ColumnInfo
    var symbol: String = "",
    @ColumnInfo
    var image: String = "",
    @ColumnInfo
    var price: String
)