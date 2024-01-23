package com.nisanth.cinergy.data.api.response

import com.google.gson.annotations.SerializedName

data class ResToken(
    @SerializedName("response") val response: String? = null,
    @SerializedName("location") val location: Any? = null,
    @SerializedName("printer_name") val printerName: Any? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("food_menu") val foodMenu: Int? = null,
    @SerializedName("attractions_menu") val attractionsMenu: Int? = null,
    @SerializedName("token") val token: String? = null
)
