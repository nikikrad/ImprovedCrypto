package com.example.improvedcrypto.files.main.dataclass

import com.google.gson.annotations.SerializedName

class CoinResponse(
    var id: String,
    var name: String,
    var symbol: String,
    var image: String,
    @SerializedName("current_price")
    var price: String
)