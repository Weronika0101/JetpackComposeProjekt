package com.example.aandroidprojekt
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.aandroidprojekt.screens.AddScreen
import com.example.aandroidprojekt.screens.DetailsScreen
import com.example.aandroidprojekt.screens.HomeScreen
import com.example.aandroidprojekt.screens.ListScreen
import com.example.aandroidprojekt.screens.ModifyScreen
import com.example.aandroidprojekt.screens.SwipeScreen

import com.example.aandroidprojekt.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val sharedViewModel = SharedViewModel()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val startDestination = "home"
            val navController = rememberNavController()

            NavigationDrawerComposeTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "list",
                                    title = "List Screen",
                                    contentDescription = "Go to list screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "swipe",
                                    title = "Swipe Screen",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {
                                when (it.id) {
                                    "home" -> {
                                        navController.navigate("home")
                                        {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        scope.launch {
                                            scaffoldState.drawerState.close() // Close the drawer after navigation
                                        }
                                    }
                                    "list" -> {
                                        navController.navigate("list")
                                        {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        scope.launch {
                                            scaffoldState.drawerState.close() // Close the drawer after navigation
                                        }
                                    }
                                    "swipe" -> {
                                        navController.navigate("swipe")
                                        {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }

                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        scope.launch {
                                            scaffoldState.drawerState.close() // Close the drawer after navigation
                                        }
                                    }
                                }
                                println("Clicked on ${it.title}")
                            }
                        )
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(navController = navController,sharedViewModel)
                        }
                        composable(route="list") {
                            ListScreen(navController = navController)
                        }
                        composable("swipe") {
                            SwipeScreen(navController = navController,sharedViewModel)
                        }
                        composable("details/{itemId}") { backStackEntry ->
                            // Extract itemId from the route
                            val itemId = backStackEntry.arguments?.getString("itemId")
                            DetailsScreen(navController = navController, itemId = itemId)
                        }
                        composable("add") {
                            AddScreen(navController = navController)
                        }
                        composable("modify/{itemId}") { backStackEntry ->
                            val itemId = backStackEntry.arguments?.getString("itemId")
                            ModifyScreen(navController = navController, itemId = itemId)
                        }
                    }
                }
            }
        }
    }
}
