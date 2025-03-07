package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.ui.screens.MainScreen
import com.example.currencyconverter.ui.screens.SettingsScreen
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import com.example.currencyconverter.viewmodel.RateViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                CurrencyConverterApp()
            }
        }
    }
}

@Composable
fun CurrencyConverterApp() {
    val navController = rememberNavController()
    val rateViewModel: RateViewModel = viewModel()
    val appModifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable(route = "main") { MainScreen(navController, rateViewModel, appModifier) }
        composable(route = "settings") { SettingsScreen(navController, rateViewModel, appModifier) }
    }
}