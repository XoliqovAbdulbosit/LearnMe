package com.example.myapplication.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.navigation.Screens
import com.example.myapplication.ui.theme.Main
import com.example.myapplication.ui.theme.Secondary
import com.example.myapplication.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(model: MainViewModel, navController: NavController) {
    var words by remember { mutableStateOf(model.getWords()) }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var option by remember {
        mutableStateOf("All")
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(Screens.Add.route) },
        ) {
            Icon(Icons.Filled.Edit, "Floating action button.")
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "LearnMe", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Main)
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
                    TextField(
                        value = option,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                        DropdownMenuItem(text = { Text("All") }, onClick = {
                            option = "All"
                            words = model.filter(option)
                            isExpanded = false
                        })
                        DropdownMenuItem(text = { Text("A-Z") }, onClick = {
                            option = "A-Z"
                            words = model.filter(option)
                            isExpanded = false
                        })
                        DropdownMenuItem(text = { Text("Z-A") }, onClick = {
                            option = "Z-A"
                            words = model.filter(option)
                            isExpanded = false
                        })
                        DropdownMenuItem(text = { Text("Newest") }, onClick = {
                            option = "Newest"
                            words = model.filter(option)
                            isExpanded = false
                        })
                        DropdownMenuItem(text = { Text("Oldest") }, onClick = {
                            option = "Oldest"
                            words = model.filter(option)
                            isExpanded = false
                        })
                    }
                }
            }
            LazyColumn {
                items(words) { word ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(vertical = 3.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = word.word,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Main
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = word.definitions,
                            fontSize = 18.sp,
                            color = Secondary
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = { navController.navigate("Edit/${word.id}/${word.word}/${word.definitions}") }) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                            }
                            IconButton(onClick = {
                                model.deleteWord(word)
                                words = model.getWords()
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}