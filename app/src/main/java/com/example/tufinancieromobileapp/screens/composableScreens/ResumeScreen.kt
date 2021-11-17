package com.example.tufinancieromobileapp.screens.composableScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tufinancieromobileapp.data.models.Cartera
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.R
import com.example.tufinancieromobileapp.screens.Screen
import com.example.tufinancieromobileapp.ui.theme.*

@Composable
fun ResumeScreen(carteras: List<Cartera>, navController: NavController) {
    Box(
        modifier = Modifier
            .background(cardNight)
            .fillMaxSize()
    ) {

        //GreetingResume()
        
        CarteraBox(carteras, navController)

    }

}


@Composable
fun CarteraBox(carteras: List<Cartera>, navController: NavController){

    LazyColumn(){
        items (carteras){
            cartera->
            CarteraRowBox(cartera, navController, carteras.indexOf(cartera))
        }
    }
}


@Composable
fun CarteraRowBox(cartera: Cartera, navController: NavController, pos: Int){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 15.dp, bottom = 15.dp, top = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Gray)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Text(
                text = "fecha emicion: " + cartera.emisionDate,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Plazo: "+cartera.plazoTaza,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Text(
                text = "fecha- descuento: " +cartera.discountDate,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Text(
                text = "fecha- pago: " +cartera.paymentDate,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )

            Text(
                text = "Retencion: "+cartera.retencion.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Text(
                text = "Tasa efectiva: "+cartera.tasaEfectiva.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Text(
                text = "Total facturado:" +cartera.totalFacturado.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            /*  Button(onClick = { /*TODO*/ }, modifier = Modifier.align(CenterHorizontally).background(
                  Color.Transparent)) {*/
                Icon(
                    painter = painterResource(id = R.drawable.mooore),
                    contentDescription = "Google sign button",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .align(CenterHorizontally)
                        .clickable { navController.navigate(Screen.ResumeDetailScreen.withArgs(pos.toString())) }
                )
          /*  }*/

        }


    }
}


@Composable
fun GreetingResume(){
    Box(modifier = Modifier.padding(bottom = 50.dp)){
        Text(text = "Bienvenido a tu cartera, guapo")
        Spacer(modifier = Modifier.height(20.dp))
    }
}