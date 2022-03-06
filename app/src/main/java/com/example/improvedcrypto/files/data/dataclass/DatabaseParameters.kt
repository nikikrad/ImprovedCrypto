package com.example.improvedcrypto.files.data.dataclass

data class DatabaseParameters(
    var symbol: String,
    var name: String,
    var image: String,
    var description: String,
    var currentPrice: Float,
    var changePrice: Float
)
