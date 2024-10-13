package com.example.stockmarketapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockmarketapp.Model.apiResponse
import com.example.test.RecentDayData


@Composable
fun HomeScreen(viewModel: StockViewModel, navController: NavController, data: apiResponse?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        IconButton(onClick = {
            viewModel.resetResult()
            navController.popBackStack()
                             },
            modifier = Modifier.padding(start = 20.dp, top = 80.dp)
            ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(40.dp), tint = Color.LightGray)
        }


        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(320.dp),
            colors = CardDefaults.cardColors(Color.LightGray)) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(
                        text = data?.metaData?.symbol.toString().toUpperCase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                        )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(text =" Open :-${data?.timeSeriesDaily?.`2024-10-11`?.open.toString()}", fontWeight = FontWeight.Bold)

                        Text(text ="Close:-${data?.timeSeriesDaily?.`2024-10-11`?.close.toString()}", fontWeight = FontWeight.Bold)

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(text ="High:-${data?.timeSeriesDaily?.`2024-10-11`?.high.toString()}" , fontWeight = FontWeight.Bold)

                        Text(text = "Low:-${data?.timeSeriesDaily?.`2024-10-11`?.low.toString()}", fontWeight = FontWeight.Bold)

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(text = "Volume:-${data?.timeSeriesDaily?.`2024-10-11`?.volume.toString()}", fontWeight = FontWeight.Bold)

                        Text(text = "Date:-${data?.metaData?.lastRefreshed.toString()}", fontWeight = FontWeight.Bold)

                    }
                }

        }

    }
}