package com.maciejpelcapps.cryptocurrencyinfoapp.presentation.coinlist

import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList<Coin>(),
    val error: String = ""
)
