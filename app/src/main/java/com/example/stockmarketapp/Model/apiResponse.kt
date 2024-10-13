package com.example.stockmarketapp.Model

import com.google.gson.annotations.SerializedName

data class apiResponse(
    @SerializedName("Meta Data")
    val metaData: MetaData?,
    @SerializedName("Time Series (Daily)")
    val timeSeriesDaily: TimeSeriesDaily?
)