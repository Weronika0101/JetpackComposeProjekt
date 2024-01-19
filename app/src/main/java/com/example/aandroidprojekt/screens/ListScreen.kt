package com.example.aandroidprojekt.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aandroidprojekt.MainViewModel
import com.example.aandroidprojekt.R
import com.example.aandroidprojekt.data.DBItem

@Composable
fun getIconForItemType(itemType: String): List<Any> {
    return when (itemType.strip().lowercase()) {
        "gimnastyka" ->  listOf(ImageVector.vectorResource(id = R.drawable.ic_gimnastyka), Color.Cyan)
        "koszykówka" -> listOf(ImageVector.vectorResource(id = R.drawable.ic_kosz), Color.Red)
        "tenis" -> listOf(ImageVector.vectorResource(id = R.drawable.ic_tenis),Color.Yellow)
        "piłka ręczna" -> listOf(ImageVector.vectorResource(id = R.drawable.ic_reczna), Color.Magenta)
        else -> listOf(ImageVector.vectorResource(id = R.drawable.ic_tenis),Color.Yellow) // Use a default icon for unknown types
    }
}


@Composable
fun ListScreen(navController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val itemsState = viewModel.getContacts()?.collectAsState(initial = emptyList())
    // LaunchedEffect will execute the block only once when the composable is first launched
    LaunchedEffect(viewModel) {
        // Trigger the fetch of data from the database when the composable is launched
        viewModel.getContacts()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val items = viewModel.getContacts()?.collectAsState(initial = emptyList())
        if (items != null) {
            ItemList(
                navController,
                items = items.value,
                onDelete = { item -> viewModel.deleteContact(item) })
        }
        FloatingActionButton(
            onClick = {
                navController.navigate("add")
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }


    }


@Composable
fun ItemList(navController: NavHostController,items: List<DBItem>, onDelete: ((DBItem) -> Unit)? = null) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemLazyColumn(navController,items, onDelete)
    }

}

@Composable
fun ItemLazyColumn(
    navController: NavHostController,
    contacts: List<DBItem>,
    onDelete: ((DBItem) -> Unit)? = null
) {
    LazyColumn() {
        items(items = contacts, key = { it.id }) { contact ->
            ItemRow(navController,contact, onDelete)
        }
    }
}

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun ItemRow(navController: NavHostController, dbItem: DBItem, onDelete: ((DBItem) -> Unit)? = null) {
//    var showDialog by remember { mutableStateOf(false) }
////    var originalList by remember { mutableStateOf<List<DBItem>?>(null) }
////
////    val dismissState = rememberDismissState(confirmStateChange = {
////        if (it == DismissValue.DismissedToStart) {
////            showDialog = true // Show the confirmation dialog
////            originalList = null
////        }
////        true
////    })
//
//    SwipeToDismiss(
//        state = dismissState,
//        background = {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 100.dp, end = 10.dp, top = 2.dp, bottom = 2.dp)
//                    .background(Color.Red),
//                horizontalArrangement = Arrangement.End
//            ) {
//                val imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete)
//                Icon(imageVector = imageVector, contentDescription = null)
//            }
//        },
//        dismissThresholds = { FractionalThreshold(0.5f) },
//        directions = setOf(DismissDirection.EndToStart)
//    ) {
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(1.dp),
//            shape = RoundedCornerShape(10.dp),
//            elevation = 1.dp
//        ) {
//            Row(
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .clickable {
//                        // Navigate to the details screen when clicked
//                        navController.navigate("details/${dbItem.id}")
//                    },
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                val result = getIconForItemType(dbItem.text_sport)
//                val icon = (result[0] as ImageVector)
//                val color = result[1] as Color
//                Icon(
//                    imageVector = icon,
//                    contentDescription = null,
//                    modifier = Modifier.size(48.dp),
//                    tint = color
//                )
//                Column {
//                    Text(text = "Element:  ${dbItem.text_sport}", fontSize = 18.sp)
//                    Text(text = "Trudność: ${dbItem.item_trudnosc}", fontStyle = FontStyle.Italic)
//                }
//
//                // Show AlertDialog if showDialog is true
//                if (showDialog) {
//                    AlertDialog(
//                        onDismissRequest = {
//                            // Dismiss the dialog if user taps outside of it
//                            showDialog = false
//                        },
//                        title = { Text(text = "Confirm Deletion") },
//                        text = { Text(text = "Are you sure you want to delete this item?") },
//                        confirmButton = {
//                            Button(
//                                onClick = {
//                                    // Invoke onDelete and dismiss the dialog if confirmed
//                                    onDelete?.invoke(dbItem)
//                                    showDialog = false
//                                }
//                            ) {
//                                Text("Yes")
//                            }
//                        },
//                        dismissButton = {
//                            Button(
//                                onClick = {
//                                    // Restore the original list and dismiss the dialog if canceled
//                                    originalList?.let { list ->
//                                        // Restore the original list
//                                        (LocalContext.current as? ComponentActivity)?.onBackPressedDispatcher?.onBackPressed()
//                                    }
//                                    showDialog = false
//                                }
//                            ) {
//                                Text("Cancel")
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemRow(navController: NavHostController, dbItem: DBItem, onDelete: ((DBItem) -> Unit)? = null) {
    var showDialog by remember { mutableStateOf(false) }

    val modifier = Modifier
        .fillMaxWidth()
        .padding(1.dp)

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .combinedClickable (
                    onClick ={navController.navigate("details/${dbItem.id}")},
                    // Navigate to the details screen when clicked
                    onLongClick = { showDialog = true}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val result = getIconForItemType(dbItem.text_sport)
            val icon = (result[0] as ImageVector)
            val color = result[1] as Color
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = color
            )
            Column {
                Text(text = "Element:  ${dbItem.text_sport}", fontSize = 18.sp)
                Text(text = "Trudność: ${dbItem.item_trudnosc}", fontStyle = FontStyle.Italic)
            }

            // Show AlertDialog if showDialog is true
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog if user taps outside of it
                        showDialog = false
                    },
                    title = { Text(text = "Confirm Deletion") },
                    text = { Text(text = "Are you sure you want to delete this item?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                // Invoke onDelete and dismiss the dialog if confirmed
                                onDelete?.invoke(dbItem)
                                showDialog = false
                            }
                        ) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                // Dismiss the dialog if canceled
                                showDialog = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}
