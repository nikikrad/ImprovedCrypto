package com.example.improvedcrypto.files.presenatation.main.dataclass

import com.example.improvedcrypto.files.data.CoinEntity

data class CoinItem(
    var nameId: String,
    var symbol: String,
    var name: String,
    var image: String,
    var description: String,
    var currentPrice: Float,
    var changePrice: Float
)

fun CoinItem.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = 0,
        nameId = nameId,
        symbol = symbol,
        name = name,
        image = image,
        description = description,
        currentPrice = currentPrice,
        changePrice = changePrice
    )
}



