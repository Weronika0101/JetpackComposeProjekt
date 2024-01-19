package com.example.aandroidprojekt.ui.theme
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicText
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.aandroidprojekt.R
//
//
//class Fragment1Activity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AandroidProjektTheme {
//                // Call your Compose function here
//                Fragment1Content()
//            }
//        }
//    }
//}
//
//    // Compose content for Fragment1
//    @Composable
//    fun Fragment1Content() {
//        // Replace with the actual image resource ID
//        val imageResource = painterResource(id = R.drawable.flower2)
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Text "Powitanie"
//            BasicText(
//                text = "Powitanie",
//                style = LocalTextStyle.current
//            )
//
//            Spacer(modifier = Modifier.height(30.dp))
//            // Image
//            Image(
//                painter = imageResource,
//                contentDescription = null, // Set a proper content description
//                modifier = Modifier
//                    .size(304.dp, 223.dp)
//                    .clip(MaterialTheme.shapes.medium)
//            )
//
//            Spacer(modifier = Modifier.height(30.dp))
//
//
//            // Text "info"
//            BasicText(
//                text = "info",
//                style = LocalTextStyle.current
//            )
//        }
//    }

//@Preview
//@Composable
//fun Fragment1Preview() {
//    AandroidProjektTheme {
//        Fragment1Content()
//    }
////    Fragment1Activity().Fragment1Content()
//}
