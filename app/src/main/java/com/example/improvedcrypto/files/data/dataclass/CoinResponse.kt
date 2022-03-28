package com.example.improvedcrypto.files.data.dataclass

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CoinResponse(
    var id: String?,
    var name: String?,
    var symbol: String?,
    var image: String?,
    @SerializedName("current_price")
    var price: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeString(image)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinResponse> {
        override fun createFromParcel(parcel: Parcel): CoinResponse {
            return CoinResponse(parcel)
        }

        override fun newArray(size: Int): Array<CoinResponse?> {
            return arrayOfNulls(size)
        }
    }
}