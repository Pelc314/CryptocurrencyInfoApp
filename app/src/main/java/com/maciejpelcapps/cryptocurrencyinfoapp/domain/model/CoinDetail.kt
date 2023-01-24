package com.maciejpelcapps.cryptocurrencyinfoapp.domain.model

import com.maciejpelcapps.cryptocurrencyinfoapp.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val description: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>,
    val type: String
)
