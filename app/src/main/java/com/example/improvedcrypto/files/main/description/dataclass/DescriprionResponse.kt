package com.example.improvedcrypto.files.main.description.dataclass

import com.google.gson.annotations.SerializedName

data class DescriptionResponse(
    var id: String,
    var name: String,
    var symbol: String,
    var image: ImageCoin,
    var description: ExtendDescription,
    var market_data: MarketData
)

data class ExtendDescription(
    var en: String
)

data class MarketData(
    var current_price: Int,
    @SerializedName("price_change_percentage_1h_in_currency")
    var changePrice: Int
)

data class ImageCoin(
    @SerializedName("small")
    var smallImage: String
)

