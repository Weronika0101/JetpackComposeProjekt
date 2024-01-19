package com.example.aandroidprojekt.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aandroidprojekt.R
import com.example.aandroidprojekt.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    var selectedPage by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    val horizontalPagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }
//    val horizontalPagerState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f
//    ) {
//        // provide pageCount
//    }

    val totalPages = 3  // Number of tabs/pages

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
        // TabRow with tabs
//        TabRow(
//            selectedTabIndex = selectedPage,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colors.primary)
//                .clickable { scope.launch { horizontalPagerState.scrollToPage(selectedPage) } },
//        ) {
//            repeat(totalPages) { index ->
//                Tab(
//                    selected = selectedPage == index,
//                    onClick = {
//                        selectedPage = index
//                    },
//                    text = { Text("Page ${index + 1}") }
//                )
//            }
//        }

        // HorizontalPager for pages
        HorizontalPager(
            state = rememberPagerState(
                initialPage = 0,
                initialPageOffsetFraction = 0f
            ) {
                3
            },
                    modifier = Modifier
                .fillMaxSize()
//                .weight(1f)
        ) { page ->
            selectedPage = page
            when (selectedPage) {
                0 -> PhotoPage(0,sharedViewModel,selectedPage)
                1 -> PhotoPage(1,sharedViewModel,selectedPage)
                2 -> PhotoPage(2,sharedViewModel,selectedPage)
            }

        }

       // Spacer(modifier = Modifier.height(16.dp))
  //  }
}


@Composable
fun PhotoPage(page: Int,sharedViewModel: SharedViewModel,selectedPage:Int) {

    val context = LocalContext.current
    val imageResource = when (page) {
        0 -> R.drawable.flower
        1 -> R.drawable.flower1
        2 -> R.drawable.flower2
        else -> throw IllegalArgumentException("Invalid page number")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                sharedViewModel.setSelectedPhoto(selectedPage)
                Log.d("selected page:", selectedPage.toString())
                Log.d("page:", page.toString())
                Toast.makeText(
                    context,
                    "You chose picture ${page+1}.",
                    Toast.LENGTH_LONG)
                    .show()

            }
        ) {
            Text("Choose")

           // showChooseToast(page)

        }
    }
}
@Composable
fun showChooseToast(page: Int) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        Toast.makeText(context, "Wybrałeś obrazek $page", Toast.LENGTH_SHORT).show()
        // You can also navigate to another screen after showing the toast if needed
    }
}