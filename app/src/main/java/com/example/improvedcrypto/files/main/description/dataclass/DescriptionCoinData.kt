package com.example.improvedcrypto.files.main.description.dataclass

import com.google.gson.annotations.SerializedName

data class DescriptionCoinData(
    var id: String,
    var name: String,
    var symbol: String,
    var image: String,
    var price: String,
    var description: String
)
