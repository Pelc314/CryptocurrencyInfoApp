package com.maciejpelcapps.cryptocurrencyinfoapp.data.mappers // ktlint-disable filename

import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.CoinDetailDto
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.model.CoinDetail

fun CoinDetailDto.toCointDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        description = description,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol,
        tags = tags.map { it.name },
        team = team,
        type = type
    )
}
