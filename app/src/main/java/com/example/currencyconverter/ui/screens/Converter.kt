package com.example.currencyconverter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.topbars.MainTopAppBar
import com.example.currencyconverter.viewmodel.RateViewModel

@Composable
fun ConverterScreen(navController: NavController, rateViewModel: RateViewModel, modifier: Modifier) {
    Scaffold(
        topBar = { MainTopAppBar(stringResource(R.string.converter_title), navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.current_rate_label, rateViewModel.selectedTargetRate, rateViewModel.selectedCurrency),
                modifier = modifier.padding(top = 6.dp),
                fontSize = 18.sp
            )
            Text(
                text = stringResource(R.string.updated_time_label, rateViewModel.convertedDatetime),
                modifier = modifier,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
            OutlinedTextField(
                value = rateViewModel.amountInput,
                onValueChange = { rateViewModel.amountInput = it },
                label = {Text(stringResource(R.string.amount_field_label))},
                trailingIcon = { Text(stringResource(R.string.amount_field_eur)) },
                singleLine = true,
                modifier = modifier,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
            )
            Text(
                text = "${rateViewModel.convertedAmount} ${rateViewModel.selectedCurrency}",
                modifier = modifier,
                fontSize = 18.sp
            )


        }
    }
}