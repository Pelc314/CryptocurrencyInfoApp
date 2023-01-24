package com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("isa_active")
    val isActive: Boolean,
    @SerializedName("isa_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
