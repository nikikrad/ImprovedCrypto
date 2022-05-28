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
    var nameId: String = "",
    var symbol: String = "",
    var name: String = "",
    var image: String = "",
    var description: String = "",
    var currentPrice: Float = 0f,
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