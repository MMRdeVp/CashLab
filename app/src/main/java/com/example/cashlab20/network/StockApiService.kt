package com.example.cashlab20.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

    @GET("time_series")
    fun getStockHistory(
        @Query("symbol") symbol: String,
        @Query("interval") interval: String = "1day",
        @Query("apikey") apiKey: String = "6fb4cee8a6b1490fb6828bc3f1502d7c"
    ): Call<StockHistoryResponse>
}
