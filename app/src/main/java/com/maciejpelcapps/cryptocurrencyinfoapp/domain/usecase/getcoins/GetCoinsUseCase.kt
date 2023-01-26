package com.maciejpelcapps.cryptocurrencyinfoapp.domain.usecase.getcoins

import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.Coin
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinPaprikaRepository
) {
    suspend fun getCoins(): Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}
