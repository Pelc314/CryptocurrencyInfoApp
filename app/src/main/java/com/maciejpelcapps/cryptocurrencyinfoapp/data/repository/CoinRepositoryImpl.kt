package com.maciejpelcapps.cryptocurrencyinfoapp.data.repository

import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.CoinPaprikaApi
import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDetailDto
import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDto
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinPaprikaRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}
