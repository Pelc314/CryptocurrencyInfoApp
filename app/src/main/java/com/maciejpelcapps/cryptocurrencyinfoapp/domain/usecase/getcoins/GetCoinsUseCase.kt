package com.maciejpelcapps.cryptocurrencyinfoapp.domain.usecase.getcoins

import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.data.mappers.toCoin
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.Coin
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinPaprikaRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) { // this exception happens when we get a response code which doesn't start with 2xx it most often means that we got an error
            emit(Resource.Failure(e.localizedMessage ?: "Unexpected http error occured"))
        } catch (e: IOException) { // this exception means our repository or api cannot talk to the remote API for example lack of internet conection
            emit(
                Resource.Failure(
                    e.localizedMessage
                        ?: "Unexpected IO error occured, check your internet connection"
                )
            )
        }
    }
}
