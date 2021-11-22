package com.example.tufinancieromobileapp.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tufinancieromobileapp.data.models.AuthViewModel
import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.screens.composableScreens.*
import com.example.tufinancieromobileapp.utils.presentation.AuthScreen

@ExperimentalMaterialApi
@Composable
fun Navigation(_carteras: List<Cartera>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplasScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.ResumeScreen.route) {
            ResumeScreen(carteras = _carteras, navController)
        }
        composable(route = Screen.ValueTypeScreen.route) {
            TypeOfValue(navController)
        }
        composable(route = Screen.SplasScreen.route){
            splashScreen(navController = navController)
        }
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(authViewModel = AuthViewModel(), navController = navController)
        }
        composable(route = Screen.ResumeDetailScreen.route + "/{posNum}", arguments = listOf(
            navArgument("posNum"){
                type = NavType.IntType
                defaultValue = 1
                nullable = false
            }
        )){
            entry ->
            entry.arguments?.getInt("posNum")
                ?.let { resumeDetail(navController = navController,documentItem = _carteras[it]) }
        }

        composable(route = Screen.FacturaScreen.route + "/{type}", arguments = listOf(
            navArgument("type") {
                type = NavType.IntType
                defaultValue = 1
                nullable = false
            }
        )) { entry ->
            entry.arguments?.getInt("type")
                ?.let { FacturaScreen(navController = navController, _typeOfValue = it) }
        }

    }
}