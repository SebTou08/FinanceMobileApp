package com.example.tufinancieromobileapp.screens.composableScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.screens.Screen
import com.example.tufinancieromobileapp.ui.theme.TransparentBlack
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainScreen(navController: NavController) {
    Box(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 15.dp, top = 35.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Transparent)
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(TransparentBlack)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    imageModel = "https://www.asap.com.ve/wp-content/uploads/2017/11/dt.common.streams.StreamServer-1.jpg",
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 4000),
                    modifier = Modifier
                        .size(200.dp, 320.dp)
                        .clip(RoundedCornerShape(25.dp))
                )

                Spacer(modifier = Modifier.height(30.dp))
                OutlinedButton(
                    onClick = {
                        navController.navigate(Screen.ResumeScreen.route)
                    }) {
                    Text("Go to resume")
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedButton(
                  
                    onClick = {
                    navController.navigate(Screen.FacturaScreen.route)
                }) {
                    Text("Create a new History")
                }

            }

        }
    }
}