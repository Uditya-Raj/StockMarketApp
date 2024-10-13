package com.example.stockmarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockmarketapp.Model.apiResponse
import com.example.stockmarketapp.ui.theme.StockmarketAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: StockViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = StockViewModel()

        setContent {
            StockmarketAppTheme {
                val stockResult = viewModel.stockResult.observeAsState()
                val navController = rememberNavController()
                val data:apiResponse?
                stockResult.value.let { stockdata ->
                    data = stockdata
                }
                NavHost(navController = navController, startDestination = SearchScreen.route ) {
                    composable(SearchScreen.route) {

                       SearchScreen(viewModel,navController)
                    }
                    composable(HomeScreen.route) {
                        HomeScreen(viewModel,navController,data)
                    }
                }

                }
            }
        }


}




