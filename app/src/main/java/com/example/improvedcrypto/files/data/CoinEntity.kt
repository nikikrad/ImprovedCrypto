package com.example.improvedcrypto.files.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem


@Entity(tableName = "coin_table", indices = [Index(value = ["name"], unique = true)])
class CoinEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var nameId: String = "",
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

fun CoinEntity.toCoinItem(): CoinItem {
    return CoinItem(
        nameId = nameId,
        symbol = symbol,
        name = name,
        image = image,
        description = description,
        currentPrice = currentPrice,
        changePrice = changePrice
    )
}