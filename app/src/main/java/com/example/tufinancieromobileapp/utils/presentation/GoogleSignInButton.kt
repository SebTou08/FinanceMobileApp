package com.example.tufinancieromobileapp.utils.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tufinancieromobileapp.R
import com.example.tufinancieromobileapp.ui.theme.Gray
import com.example.tufinancieromobileapp.ui.theme.Shapes
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterialApi
@Composable
fun GoogleSignInButtonUi(
    text: String = "",
    loadingText: String = "",
    onClicked: () -> Unit
) {

    Greetings()
   // Spacer(modifier = Modifier.height(16.dp))

    GlideImage(
        imageModel = R.drawable.tufinan,
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 5000),
        modifier = Modifier
            .size(300.dp, 320.dp)
            .clip(RoundedCornerShape(25.dp))
            .padding(top = 0.dp, start = 0.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))


    var clicked by remember { mutableStateOf(false) }
    Surface(
        onClick = { clicked = !clicked },
        shape = Shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colors.surface
    ) {


        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.googli),
                contentDescription = "Google sign button",
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (clicked) loadingText else text)

            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
                onClicked()
            }

        }
    }


}


@Composable
fun Greetings() {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .background(Gray)
            .clip(RoundedCornerShape(20.dp))
    ) {

        Text(
            text = "Bienvenido a TU FINANCIERO MOBILE APP",
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )


    }

}