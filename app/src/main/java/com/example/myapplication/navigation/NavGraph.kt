package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.view.AddScreen
import com.example.myapplication.view.EditScreen
import com.example.myapplication.view.MainScreen
import com.example.myapplication.viewmodel.MainViewModel

@Composable
fun SetNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        val model = MainViewModel(context)
        composable(Screens.Home.route) {
            MainScreen(model, navController)
        }
        composable(Screens.Add.route) {
            AddScreen(model, navController)
        }
        composable(
            route = Screens.Edit.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("word") { type = NavType.StringType },
                navArgument("definitions") { type = NavType.StringType },
        )) {
            EditScreen(
                it.arguments?.getInt("id")!!,
                it.arguments?.getString("word")!!,
                it.arguments?.getString("definitions")!!,
                model,
                navController
            )
        }
    }
}