package com.example.tufinancieromobileapp.screens.composableScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.data.remote.interfaces.ApiClient
import com.example.tufinancieromobileapp.screens.Screen
import com.example.tufinancieromobileapp.ui.theme.DeepBlack
import com.example.tufinancieromobileapp.ui.theme.Gray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FacturaScreen( navController: NavController, _typeOfValue: Int) {
    val conttext = LocalContext.current

    Toast.makeText(
        conttext,
        "Registro pasado exitosamente: $_typeOfValue",
        Toast.LENGTH_LONG
    ).show()

    var fechaEmision by remember { mutableStateOf("") }
    var fechaPago by remember { mutableStateOf("") }
    var totalFacturado by remember { mutableStateOf("") }
    var retencion by remember { mutableStateOf("") }
    var motiv by remember { mutableStateOf("") }
    var plazo by remember { mutableStateOf("") }
    var tasaEfectiva by remember { mutableStateOf("") }
    var fechaDescuento by remember { mutableStateOf("") }
    val context = LocalContext.current
    var valueType by remember { mutableStateOf("") }
    if(_typeOfValue == 0){
        valueType = "Factura"
    }
    else if (_typeOfValue == 1){
        valueType = "Letra"
    }
    else if(_typeOfValue==2){
        valueType="Recibo"
    }


    Box(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    )
    {

        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = valueType,
            modifier = Modifier.align(CenterHorizontally))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = fechaEmision,
                onValueChange = { fechaEmision = it },
                label = { Text("Fecha de emision") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = fechaPago,
                onValueChange = { fechaPago = it },
                label = { Text("Fecha de Pago") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = totalFacturado,
                onValueChange = { totalFacturado = it },
                label = { Text("Total facturado") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = retencion,
                onValueChange = { retencion = it },
                label = { Text("Retencion") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = motiv,
                onValueChange = { motiv = it },
                label = { Text("Motivo") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = plazo,
                onValueChange = { plazo = it },
                label = { Text("Plazo") }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = tasaEfectiva,
                onValueChange = { tasaEfectiva = it },
                label = { Text("Tasa Efectiva") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = fechaDescuento,
                onValueChange = { fechaDescuento = it },
                label = { Text("Fecha descuento") }
            )


            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
                onClick = {
                    val saveDataService = ApiClient.buildCartera()

                    saveDataService?.saveCartera(
                        Cartera(
                            (0 until 99999).random(),
                            fechaEmision,
                            fechaPago,
                            totalFacturado.toLong(),
                            retencion.toLong(),
                            motiv.toInt(),
                            plazo,
                            tasaEfectiva.toFloat(),
                            fechaDescuento
                        )
                    )?.enqueue(object : Callback<Cartera> {
                        override fun onResponse(call: Call<Cartera>, response: Response<Cartera>) {
                            if (response.isSuccessful) {
                                val id = response.body()!!.id
                                Toast.makeText(
                                    context,
                                    "Nuevo registro con id: $id",
                                    Toast.LENGTH_LONG
                                ).show()
                                Thread.sleep(3_000)
                                navController.navigate(Screen.ResumeScreen.route)
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Cartera>, t: Throwable) {
                            Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                        }

                    })
                }) {

            }
        }


    }
}