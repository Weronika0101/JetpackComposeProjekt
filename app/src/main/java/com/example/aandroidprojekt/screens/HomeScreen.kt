package com.example.aandroidprojekt.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.FloatState
import androidx.compose.runtime.asIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aandroidprojekt.R
import com.example.aandroidprojekt.SharedViewModel


import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavHostController, sharedViewModel:SharedViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val selectedPhotoIndex = sharedViewModel.selectedPhoto.intValue
    Log.d("dupa", selectedPhotoIndex.toString())
   // val selectedPhotoIndex = viewModel.selectedPhoto.value
    // Replace with the actual image resource IDs
    val imageResources = listOf(
        R.drawable.flower,
        R.drawable.flower1,
        R.drawable.flower2
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text "Powitanie"
        Text(
            text = "Witaj!",
            style = LocalTextStyle.current,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Image
        Image(
            painter = painterResource(id = imageResources[selectedPhotoIndex]),
            contentDescription = null,
            modifier = Modifier
                .size(304.dp, 223.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Text "info"
        Text(
            text = "info",
            style = LocalTextStyle.current,
            fontSize = 18.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

    }
}

