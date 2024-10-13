package com.example.stockmarketapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: StockViewModel, navController: NavController) {
    var stock by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.resetResult()
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 20.dp),


    ) {
        Text(text = "Search Stocks", fontSize = 30.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = stock, onValueChange = {stock=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            placeholder = { Text(text = "Enter Symbol")},
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null)},
            trailingIcon = {
                if(stock.isNotEmpty()){
                    IconButton(onClick = { stock ="" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null,
                            modifier = Modifier.background(shape = CircleShape, color = Color.LightGray)
                        )
                    }
                }

            },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent,
                containerColor = Color.LightGray.copy(.3f)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                     viewModel.viewModelScope.launch {
                         if (stock.isNotEmpty()) {
                             val result = viewModel.getData(stock)
                             viewModel.stockResult.postValue(result?.body())
                             keyboardController?.hide()
                             navController.navigate(HomeScreen.route)
                         }
                         keyboardController?.hide()
                     }

                }
            )
        )

    }
}