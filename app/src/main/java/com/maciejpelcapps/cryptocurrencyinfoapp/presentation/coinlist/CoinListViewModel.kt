package com.maciejpelcapps.cryptocurrencyinfoapp.presentation.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.usecase.getcoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf<CoinListState>(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase.getCoins().collect { flowResult ->
                when (flowResult) {
                    is Resource.Success -> {
                        _state.value = CoinListState(coins = flowResult.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value =
                            CoinListState(error = flowResult.message ?: "Unexpected Error!! O_O")
                    }
                    is Resource.Loading -> {
                        _state.value = CoinListState(isLoading = true)
                    }
                }
            }
        }
    }
}
