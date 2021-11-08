package com.example.tufinancieromobileapp.screens.composableScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.ui.theme.DeepBlack
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tufinancieromobileapp.ui.theme.TransparentBlack

@Composable
fun ResumeScreen(carteras: List<Cartera>) {
    Box(
        modifier = Modifier
            .background(DeepBlack)
            .fillMaxSize()
    ) {

        //GreetingResume()
        
        CarteraBox(carteras)

    }

}


@Composable
fun CarteraBox(carteras: List<Cartera>){

    LazyColumn(){
        items (carteras){
            cartera->
            CarteraRowBox(cartera)
        }
    }
}


@Composable
fun CarteraRowBox(cartera: Cartera){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 15.dp, bottom = 15.dp, top = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(TransparentBlack)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Text(
                text = "fecha emicion: " + cartera.fechaEmision,
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Plazo: "+cartera.plazo,
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )
            Text(
                text = "fecha- descuento: " +cartera.fechaDescuento,
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )
            Text(
                text = "fecha- pago: " +cartera.fechaPago,
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )

            Text(
                text = "Retencion: "+cartera.retencion.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )
            Text(
                text = "Tasa efectiva: "+cartera.tasaEfectiva.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )
            Text(
                text = "Total facturado:" +cartera.totalFacturado.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.LightGray
            )

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