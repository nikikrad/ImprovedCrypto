package com.example.improvedcrypto.files.presenatation.main.description.dataclass

import com.google.gson.annotations.SerializedName

data class ResponseDescription(
    var id: String,
    var symbol: String,
    var name: String,
    var image: Image,
    var description: ExtendDescription,
    @SerializedName("market_data")
    var marketData: MarketData
)

data class ExtendDescription(
    var en: String
)

data class MarketData(
    @SerializedName("current_price")
    var currentPrice: CurrentPrice,
    @SerializedName("price_change_percentage_24h")
    var changePrice: Float
)

data class CurrentPrice(
    var usd: Float
)

data class Image(
    var large: String
)


