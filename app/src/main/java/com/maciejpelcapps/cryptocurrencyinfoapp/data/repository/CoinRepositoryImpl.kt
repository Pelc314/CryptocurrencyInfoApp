package com.maciejpelcapps.cryptocurrencyinfoapp.data.repository

import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.data.mappers.toCoin
import com.maciejpelcapps.cryptocurrencyinfoapp.data.mappers.toCointDetail
import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.CoinPaprikaApi
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.Coin
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.CoinDetail
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinPaprikaRepository {
    override suspend fun getCoins(): Flow<Resource<List<Coin>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val coins = api.getCoins().map { it.toCoin() }
                emit(Resource.Success(coins))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<Coin>>(
                        e.localizedMessage ?: "Unexpected http error occured"
                    )
                )
            } catch (e: IOException) { // this exception means our repository or api cannot talk to the remote API for example lack of internet conection
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Unexpected IO error occured, check your internet connection"
                    )
                )
            }
        }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val coin = api.getCoinById(coinId).toCointDetail()
                emit(Resource.Success(coin))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "Unexpected http error occured"
                    )
                )
            } catch (e: IOException) { // this exception means our repository or api cannot talk to the remote API for example lack of internet conection
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Unexpected IO error occured, check your internet connection"
                    )
                )
            }
        }
    }
}
