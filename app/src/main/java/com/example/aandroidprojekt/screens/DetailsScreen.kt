package com.example.aandroidprojekt.screens

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aandroidprojekt.MainViewModel
@Composable
fun DetailsScreen(navController: NavHostController, itemId: String?, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val item = itemId?.let { viewModel.getContactById(it) }

    // Check if item is not null before accessing its value
    if (item != null) {
        Log.d("DetailsScreen", "Item: $item")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.text_sport,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            )

            Text(
                text = "Opis",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Text(
                text = item.item_opis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            )

            Text(
                text = "Trudność:    ${item.item_trudnosc}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Slider(
                value = item.item_trudnosc.toFloat(),
                onValueChange = { /* Handle value change */ },
                valueRange = 0f..10f,
                steps = 4,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dla początkujących:  ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Checkbox(
                    checked = item.item_beginner,
                    enabled = false,
                    onCheckedChange = { /* Handle checkbox state change */ },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text("Back")
                }

                Button(
                    onClick = { navController.navigate("modify/${item.id}")},
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text("Modify")
                }
            }

        }
    }
}

