package com.example.currencyconverter.model

import com.example.currencyconverter.BuildConfig
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class RateInfo (
    @SerializedName("time_last_update_utc") var timeLastUpdateUtc: String,
    @SerializedName("conversion_rates") var conversionRates : Map<String, Float>
)

const val BASE_URL = "https://v6.exchangerate-api.com/v6/${BuildConfig.API_KEY}/latest/"

interface RateApi {
    @GET("EUR")
    suspend fun getRateInfo(): RateInfo

    companion object {
        var rateService: RateApi? = null

        fun getInstance(): RateApi {
            if(rateService === null) {
                rateService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RateApi::class.java)
            }
            return rateService!!
        }
    }
}