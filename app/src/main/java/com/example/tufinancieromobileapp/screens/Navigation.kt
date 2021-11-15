package com.example.tufinancieromobileapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.tufinancieromobileapp.data.models.Cartera
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tufinancieromobileapp.screens.composableScreens.FacturaScreen
import com.example.tufinancieromobileapp.screens.composableScreens.MainScreen
import com.example.tufinancieromobileapp.screens.composableScreens.ResumeScreen
import com.example.tufinancieromobileapp.screens.composableScreens.TypeOfValue

@Composable
fun Navigation (_carteras: List<Cartera>){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ResumeScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.ResumeScreen.route){
            ResumeScreen(carteras = _carteras)
        }
        composable(route = Screen.ValueTypeScreen.route){
            TypeOfValue(navController)
        }
        composable(route = Screen.FacturaScreen.route + "/{type}", arguments = listOf(
            navArgument("type"){
                type = NavType.IntType
                defaultValue = 1
                nullable = false
            }
        )){
            entry ->
            entry.arguments?.getInt("type")?.let { FacturaScreen(navController = navController, _typeOfValue = it) }
        }
    }
}