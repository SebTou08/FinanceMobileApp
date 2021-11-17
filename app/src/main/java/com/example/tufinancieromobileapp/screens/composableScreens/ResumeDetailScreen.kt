package com.example.tufinancieromobileapp.screens.composableScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.data.models.User
import com.example.tufinancieromobileapp.ui.theme.Gray
import com.example.tufinancieromobileapp.ui.theme.TransparentBlack
import com.example.tufinancieromobileapp.ui.theme.cardNight


@Composable
fun resumeDetail(navController: NavController, documentItem: Cartera) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .background(cardNight)
                .clip(RoundedCornerShape(25.dp))
                .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
                .fillMaxSize()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(TransparentBlack)


            ) {
                Column() {
                    Spacer(modifier = Modifier.height(30.dp))
                    userContatInformation(user = documentItem.emisor)
                    Spacer(modifier = Modifier.height(30.dp))

                    givenInformation(document = documentItem)
                    Spacer(modifier = Modifier.height(30.dp))

                    calculatedInformation(document = documentItem)


                }
            }

        }
    }
}

@Composable
fun userContatInformation(user: User) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 15.dp, top = 40.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Transparent)
    )
    {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "User: ",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
            Text(
                text = user.name,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Text(
                text = user.lastName,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
        }

    }
}


@Composable
fun givenInformation(document: Cartera) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Gray)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Datos brindados",
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )
        Text(
            text = "Fecha de emision: " + document.emisionDate,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Fecha de pago: " + document.paymentDate,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Fecha de descuento: " + document.discountDate,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Tasa: " + document.tasa.toString(),
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Primer costo registrado: " + document.costos.first().amount,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Retencion" + document.retencion.toString(),
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Total a facturar" + document.totalFacturado.toString(),
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
    }
}


@Composable
fun calculatedInformation(document: Cartera) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Gray)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Datos Calculados",
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )
        Text(
            text = "Costos iniciales totales: " + document.costesInicialesTotales,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Costos finales totales: " + document.costesFinalesTotales,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "TCEA: " + document.tasaCosteEfectivaAnual,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Valor a entregar: " + document.valorTotalAEntregar,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
        Text(
            text = "Valor a recibir: " + document.valorTotalARecibir,
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
    }
}