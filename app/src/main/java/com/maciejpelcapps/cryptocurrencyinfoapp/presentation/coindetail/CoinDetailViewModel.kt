package com.maciejpelcapps.cryptocurrencyinfoapp.presentation.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciejpelcapps.cryptocurrencyinfoapp.common.Constants
import com.maciejpelcapps.cryptocurrencyinfoapp.common.Resource
import com.maciejpelcapps.cryptocurrencyinfoapp.domain.usecase.getcoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId = coinId).onEach { flowResult ->
            when (flowResult) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = flowResult.data)
                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailState(error = flowResult.message ?: "Unexpected Error!! O_O")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
