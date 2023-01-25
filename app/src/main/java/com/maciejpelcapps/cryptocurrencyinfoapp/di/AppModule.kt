package com.maciejpelcapps.cryptocurrencyinfoapp.di

import com.maciejpelcapps.cryptocurrencyinfoapp.common.Constants
import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.CoinPaprikaApi
import com.maciejpelcapps.cryptocurrencyinfoapp.data.repository.CoinRepositoryImpl
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.repository.CoinPaprikaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun providePaprikaRepository(api: CoinPaprikaApi): CoinPaprikaRepository {
        return CoinRepositoryImpl(api)
    }
}
