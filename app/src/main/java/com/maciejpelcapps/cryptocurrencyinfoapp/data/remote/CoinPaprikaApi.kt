package com.maciejpelcapps.cryptocurrencyinfoapp.data.remote

import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDto
import retrofit2.http.GET

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): CoinDto
}
