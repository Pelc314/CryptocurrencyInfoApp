package com.maciejpelcapps.cryptocurrencyinfoapp.domain.usecase.getcoin

import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.data.mappers.toCointDetail
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.CoinDetail
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinPaprikaRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCointDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) { // this exception happens when we get a response code which doesn't start with 2xx it most often means that we got an error
            emit(Resource.Error(e.localizedMessage ?: "Unexpected http error occured"))
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
