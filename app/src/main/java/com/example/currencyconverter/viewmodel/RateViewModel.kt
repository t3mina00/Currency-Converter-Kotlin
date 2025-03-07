package com.example.currencyconverter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.model.RateApi
import com.example.currencyconverter.model.RateInfo
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

sealed interface RateUiState {
    data class Success(val rateInfo: RateInfo): RateUiState
    object Error: RateUiState
    object Loading: RateUiState
}

class RateViewModel: ViewModel() {
    private val df = DecimalFormat("#.##")

    var rateUiState: RateUiState by mutableStateOf<RateUiState>(RateUiState.Loading)
        private set

    // Rate list from API
    var rates = mutableStateListOf<Pair<String, Float>>()
        private set

    private var updateRateDatetime = mutableStateOf("--.--.---- --:--:--")

    val convertedDatetime: String
        get() {
            return convertToTargetRegionTime(updateRateDatetime.value, "Europe/Helsinki")
        }

    var selectedCurrency by mutableStateOf("")

    var selectedTargetRate by mutableStateOf("0.00")

    private val targetRate: Float
        get() {
            return selectedTargetRate.toFloatOrNull() ?: 0.00f
        }

    var amountInput by mutableStateOf("0.00")

    private val amount: Float
        get() {
            return amountInput.toFloatOrNull() ?: 0.00f
        }

    val convertedAmount: String
        get() {
            return df.format(amount * targetRate)
        }

    var selectedCurrencyListText by mutableStateOf("Select currency")


    init {
        getRates()
    }

    private fun getRates() {
        viewModelScope.launch() {
            var rateApi: RateApi? = null
            try {
                rateApi = RateApi.getInstance()
                val rateInfo = rateApi.getRateInfo()
                rateUiState = RateUiState.Success(rateInfo)
                rates.clear()
                rates.addAll(rateInfo.conversionRates.toList())
                updateRateDatetime.value = rateInfo.timeLastUpdateUtc

            } catch(e: Exception) {
                rateUiState = RateUiState.Error
            }
        }
    }

    // Convert UTC time coming from API to the target region's time
    private fun convertToTargetRegionTime(utcDateString: String, targetRegion: String): String {
        val originalFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
        val date = originalFormat.parse(utcDateString)

        // Set target timezone
        val finlandTimeZone = TimeZone.getTimeZone(targetRegion)

        // Format the date
        val newFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH)
        newFormat.timeZone = finlandTimeZone

        return newFormat.format(date!!)
    }

}