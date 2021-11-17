package com.example.tufinancieromobileapp.screens

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object ResumeScreen : Screen("resume_screen")
    object FacturaScreen: Screen("factura_screen")
    object ValueTypeScreen: Screen("value_type_screen")
    object AuthScreen: Screen("auth_screen")
    object SplasScreen : Screen("splash_screen")
    object ResumeDetailScreen: Screen("resume_detail_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}
