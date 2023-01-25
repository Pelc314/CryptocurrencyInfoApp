package com.maciejpelcapps.cryptocurrencyinfoapp.presentation.coindetail

import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
