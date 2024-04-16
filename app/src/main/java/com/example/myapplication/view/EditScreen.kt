package com.example.myapplication.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.database.Word
import com.example.myapplication.navigation.Screens
import com.example.myapplication.ui.theme.Main
import com.example.myapplication.ui.theme.Secondary
import com.example.myapplication.viewmodel.MainViewModel
import java.util.Date

@Composable
fun EditScreen(
    id: Int, word: String, definitions: String, model: MainViewModel, navController: NavController
) {
    var uword by remember { mutableStateOf(word) }
    var udefinitions by remember { mutableStateOf(definitions) }
    val date by remember { mutableStateOf(Date()) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "LearnMe", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Main)
        OutlinedTextField(
            value = uword,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 7.dp),
            onValueChange = { uword = it },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = "Word", fontSize = 14.sp) },
            textStyle = TextStyle(fontSize = 16.sp)
        )
        OutlinedTextField(
            value = udefinitions,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 7.dp),
            onValueChange = { udefinitions = it },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = "Definition", fontSize = 14.sp) },
            textStyle = TextStyle(fontSize = 16.sp)
        )
        FloatingActionButton(
            containerColor = Secondary,
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = {
                model.updateWord(Word(id = id, word = uword, definitions = udefinitions, date = date))
                navController.navigate(Screens.Home.route)
            }) {
            Text(
                text = "Register",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 7.dp)
            )
        }
    }
}