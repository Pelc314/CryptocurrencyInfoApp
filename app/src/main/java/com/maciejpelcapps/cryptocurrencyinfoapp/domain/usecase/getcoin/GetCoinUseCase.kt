package com.maciejpelcapps.cryptocurrencyinfoapp.domain.usecase.getcoin

import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.CoinDetail
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinPaprikaRepository
) {
    suspend fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>> {
        return repository.getCoinById(coinId)
    }
}
