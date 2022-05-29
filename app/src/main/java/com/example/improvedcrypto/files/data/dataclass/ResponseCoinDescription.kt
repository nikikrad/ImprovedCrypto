package com.example.improvedcrypto.files.data.dataclass

import com.example.improvedcrypto.files.data.CoinEntity
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

fun ResponseDescription.toCoinEntity(): CoinEntity{
    return CoinEntity(
        id = 0,
        nameId = id,
        symbol = symbol,
        name = name,
        image = image.large,
        description = description.en,
        currentPrice = marketData.currentPrice.usd,
        changePrice = marketData.changePrice
    )
}

fun ResponseDescription.toResponseDescription(): ResponseDescription {
    return ResponseDescription(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        description = description,
        marketData = marketData
    )
}


