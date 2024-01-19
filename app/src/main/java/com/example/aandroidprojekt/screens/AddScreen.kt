package com.example.aandroidprojekt.screens

import androidx.compose.material.Slider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.aandroidprojekt.MainViewModel
import com.example.aandroidprojekt.data.DBItem
import kotlinx.coroutines.launch

@Composable
fun AddScreen(navController: NavHostController, viewModel:MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    var dyscyplina by remember { mutableStateOf(TextFieldValue()) }
    var trudnosc by remember { mutableFloatStateOf(0f) }
    var opis by remember { mutableStateOf(TextFieldValue()) }
    var isPoczatkujacy by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = dyscyplina,
            onValueChange = { dyscyplina = it },
            label = { Text("Dyscyplina") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )
        Text(text="Trudność",
            modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),)

        Slider(
            value = trudnosc,
            onValueChange = { trudnosc = it },
            valueRange = 0f..10f,
            steps = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        TextField(
            value = opis,
            onValueChange = { opis = it },
            label = { Text("Opis") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            maxLines = 2,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Czy dla początkujących?")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isPoczatkujacy,
                onCheckedChange = { isPoczatkujacy = it },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    val newItem = DBItem(
                        text_sport = dyscyplina.text,
                        item_beginner = isPoczatkujacy,
                        item_trudnosc = trudnosc.toInt(),
                        item_opis = opis.text,
                        item_type = 0 // Set the appropriate value for item_type
                    )
                    viewModel.viewModelScope.launch {
                        viewModel.insertItem(newItem)
                    }

                    // Optionally, navigate back to the list screen
                    navController.popBackStack()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Zapisz")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Anuluj")
            }
        }
    }
}
