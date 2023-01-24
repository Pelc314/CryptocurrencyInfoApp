package com.maciejpelcapps.cryptocurrencyinfoapp.data.mappers // ktlint-disable filename

import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDto
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}
