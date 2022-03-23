package com.example.improvedcrypto.files.presenatation.main.dataclass

data class CoinItem(
    var nameId: String,
    var symbol: String,
    var name: String,
    var image: String,
    var description: String,
    var currentPrice: Float,
    var changePrice: Float
)

