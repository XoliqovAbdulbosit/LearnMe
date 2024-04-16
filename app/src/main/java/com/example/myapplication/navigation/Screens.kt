package com.example.myapplication.navigation

sealed class Screens(var route: String) {
    object Home: Screens("Home")
    object Add: Screens("Add")
    object Edit: Screens("Edit/{id}/{word}/{definitions}")
}