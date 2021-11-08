package com.example.tufinancieromobileapp.screens

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object ResumeScreen : Screen("resume_screen")
    object FacturaScreen: Screen("factura_screen")
    object ValueTypeScreen: Screen("value_type_screen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
