package com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository

import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDetailDto
import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDto

// this interface is helpful for the test cases,
// it doesn't have that much in common with the actual getting data from the repository
// for that we have the repository implementation
// this interface just simulates the behaviour of the API so we won't use our API quota.

interface CoinPaprikaRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}
