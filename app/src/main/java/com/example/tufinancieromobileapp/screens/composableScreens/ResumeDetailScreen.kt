package com.example.tufinancieromobileapp.screens.composableScreens

import android.provider.DocumentsContract
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.data.models.Cartera

@Composable
fun resumeDetail(navController: NavController,documentItem: Cartera){

    Text(text = documentItem.paymentDate)
}