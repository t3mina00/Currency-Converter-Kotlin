package com.example.currencyconverter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.components.CurrencyList
import com.example.currencyconverter.ui.topbars.ScreenTopBar
import com.example.currencyconverter.viewmodel.RateViewModel

@Composable
fun SettingsScreen(navController: NavController, rateViewModel: RateViewModel, modifier: Modifier) {
    Scaffold(
        topBar = { ScreenTopBar(stringResource(R.string.settings_title), navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = stringResource(R.string.convert_to_label),
                modifier = modifier.padding(top = 6.dp),
                fontSize = 18.sp
            )
            CurrencyList(rateViewModel, modifier)
        }
    }
}