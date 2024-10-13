package com.example.test

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

data class DailyData(
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val volume: String
)
object RecentDayData {
    fun getMostRecentData(jsonString: String): Pair<LocalDate, DailyData>? {
        val gson = Gson()
        val type = object : TypeToken<Map<String, Map<String, String>>>() {}.type
        val data: Map<String, Map<String, String>> = gson.fromJson(jsonString, type)

        return data.entries
            .map { (date, values) ->
                LocalDate.parse(date) to DailyData(
                    values["1. open"] ?: "",
                    values["2. high"] ?: "",
                    values["3. low"] ?: "",
                    values["4. close"] ?: "",
                    values["5. volume"] ?: ""
                )
            }
            .maxByOrNull { (date, _) -> date }
    }



//        val recentData = getMostRecentData(jsonString)
//        if (recentData != null) {
//            val (date, data) = recentData
//            println("Most recent date: $date")
//            println("Open:${data.open}")
//            println("High: ${data.high}")
////        println("Low: ${data.low}")
////        println("Close: ${data.close}")
////        println("Volume: ${data.volume}")
//        }

}