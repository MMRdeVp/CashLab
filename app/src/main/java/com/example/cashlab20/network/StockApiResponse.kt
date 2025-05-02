package com.example.cashlab20.network

data class StockHistoryResponse(
    val values: List<StockData>
)

data class StockData(
    val datetime: String,
    val close: String
)
