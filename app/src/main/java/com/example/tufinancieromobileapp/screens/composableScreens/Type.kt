package com.example.tufinancieromobileapp.screens.composableScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.screens.Screen
import com.example.tufinancieromobileapp.ui.theme.DeepBlack
import com.example.tufinancieromobileapp.ui.theme.DeepBlue
import kotlinx.coroutines.launch

@Composable
fun TypeOfValue(navController: NavController) {
    //0 es factura
    //1 es recibo
    Box(
        modifier = Modifier
            .background(DeepBlack)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            DropdownDemo(navController)
        }
    }
}


@Composable
fun DropdownDemo(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("FACTURA", "LETRA", "RECIBO", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart).padding(50.dp)
    ) {
        Text(
            items[selectedIndex], modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    Color.White
                )
                .clip(RoundedCornerShape(40.dp))
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    DeepBlue
                )
                .padding(50.dp)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }

        }


    }
    Box(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(
            onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("Something happened!")
                }
                Toast.makeText(
                    context,
                    "Nuevo registro con id: $selectedIndex",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(Screen.FacturaScreen.withArgs(selectedIndex))
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "pepe")
        }
    }
}