package com.example.currencyconverter.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.currencyconverter.viewmodel.RateUiState
import com.example.currencyconverter.viewmodel.RateViewModel

@Composable
fun MainScreen(navController: NavController, rateViewModel: RateViewModel, modifier: Modifier) {
    when(rateViewModel.rateUiState) {
        is RateUiState.Loading -> LoadingScreen()
        is RateUiState.Success -> ConverterScreen(navController, rateViewModel, modifier)
        is RateUiState.Error -> ErrorScreen(modifier)
    }
}