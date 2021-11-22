package com.example.tufinancieromobileapp.screens.composableScreens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tufinancieromobileapp.data.models.CarteraApiRequest
import com.example.tufinancieromobileapp.data.models.Costos
import com.example.tufinancieromobileapp.data.remote.interfaces.ApiClient
import com.example.tufinancieromobileapp.screens.Screen
import com.example.tufinancieromobileapp.ui.theme.Gray
import com.example.tufinancieromobileapp.ui.theme.cardNight
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.input
import com.vanpra.composematerialdialogs.listItemsSingleChoice
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun FacturaScreen(navController: NavController, _typeOfValue: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(cardNight)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            FacturaScreenDetail(navController, _typeOfValue)

        }
    }
}


@Composable
fun FacturaScreenDetail(navController: NavController, _typeOfValue: Int) {


    val conttext = LocalContext.current

    Toast.makeText(
        conttext,
        "Registro pasado exitosamente: $_typeOfValue",
        Toast.LENGTH_LONG
    ).show()

    var esNominal by remember { mutableStateOf(true) }
    var fechaEmision by remember { mutableStateOf("") }
    var fechaPago by remember { mutableStateOf("") }
    var totalFacturado by remember { mutableStateOf("") }
    var retencion by remember { mutableStateOf("") }
    var daysPerYear by remember { mutableStateOf(0) }
    var plazoTaza by remember { mutableStateOf("") }
    var tasa by remember { mutableStateOf("") }
    var periodoCapitaliza by remember { mutableStateOf(0) }
    var receptorId by remember { mutableStateOf(1) }
    var userReceptorId by remember { mutableStateOf(1) }
    var costos by remember { mutableStateOf(listOf<Costos>()) }
    var costo by remember { mutableStateOf(Costos(1, false, "0".toDouble())) }
    var carteraSending: CarteraApiRequest

    val context = LocalContext.current
    var valueType by remember { mutableStateOf("") }
    if (_typeOfValue == 0) {
        valueType = "Factura"
    } else if (_typeOfValue == 1) {
        valueType = "Letra"
    } else if (_typeOfValue == 2) {
        valueType = "Recibo"
    }


    //Plazo de tasa
    val dialogStatePeriodoCap = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStatePeriodoCap, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        listItemsSingleChoice(
            list = listOf(
                "Diario",
                "Quincenal",
                "Mensual",
                "Bimestral",
                "Timestral",
                "Semestral",
                "Anual"
            ),
            initialSelection = 0
        ) {
            when (it) {
                0 -> {
                    periodoCapitaliza = 1
                }
                1 -> {
                    periodoCapitaliza = 15
                }
                2 -> {
                    periodoCapitaliza = 30
                }
                3 -> {
                    periodoCapitaliza = 60
                }
                4 -> {
                    periodoCapitaliza = 90
                }
                5 -> {
                    periodoCapitaliza = 180
                }
                6 -> {
                    periodoCapitaliza = 360
                }
            }
            carteraSending = CarteraApiRequest(
                valueType,
                esNominal,
                fechaEmision,
                fechaPago,
                totalFacturado.toDouble(),
                retencion.toDouble(),
                daysPerYear,
                plazoTaza,
                tasa.toFloat(),
                periodoCapitaliza,
                receptorId,
                userReceptorId,
                costos
            )

        }
    }


    //tasa
    val dialogStateTasa = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateTasa, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        input(label = "Tasa", hint = "Ingrese valor") { inputString ->
            tasa = inputString
            dialogStatePeriodoCap.show()
        }
    }

    //Plazo de tasa
    val dialogStatePlazoTasa = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStatePlazoTasa, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        Text(text = "Ingrese el plazo de la tasa")
        listItemsSingleChoice(
            list = listOf(
                "Diario",
                "Quincenal",
                "Mensual",
                "Bimestral",
                "Timestral",
                "Semestral",
                "Anual"
            ),
            initialSelection = 0
        ) {
            when (it) {
                0 -> {
                    plazoTaza = "Diario"
                    dialogStateTasa.show()

                }
                1 -> {
                    plazoTaza = "Quincenal"
                    dialogStateTasa.show()

                }
                2 -> {
                    plazoTaza = "Mensual"
                    dialogStateTasa.show()

                }
                3 -> {
                    plazoTaza = "Bimestral"
                    dialogStateTasa.show()

                }
                4 -> {
                    plazoTaza = "Trimestral"
                    dialogStateTasa.show()

                }
                5 -> {
                    plazoTaza = "Semestral"
                    dialogStateTasa.show()

                }
                6 -> {
                    plazoTaza = "Anual"
                    dialogStateTasa.show()

                }
            }

        }
    }


    //diasxaños
    val dialogStateDiasXAnios = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateDiasXAnios, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        input(label = "Dias por año", hint = "Ingrese numero") { inputString ->
            daysPerYear = inputString.toInt()
            dialogStatePlazoTasa.show()
        }
    }

    //Retencion

    val dialogStateRetencion = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateRetencion, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        input(label = "Retencion", hint = "Ingrese cantidad") { inputString ->
            retencion = inputString
            dialogStateDiasXAnios.show()
        }
    }


    //TotalFacturado
    val dialogStateTotalFacturado = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateTotalFacturado, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        input(label = "Total facturado", hint = "Ingrese cantidad") { inputString ->
            totalFacturado = inputString
            dialogStateRetencion.show()
        }
    }

    //NominalOEfectiva
    val dialogStateNominalOEfectiva = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateNominalOEfectiva, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        listItemsSingleChoice(
            list = listOf("Nominal", "Efectiva"),
            initialSelection = 1
        ) {
            when (it) {
                0 -> {
                    esNominal = true
                    dialogStateTotalFacturado.show()

                }
                1 -> {
                    esNominal = false
                    dialogStateTotalFacturado.show()
                }
            }
        }
    }

    //Fecha de pago
    val dialogStatePago = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogStatePago,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        Text(text = "Ingrese la fecha de pago")
        datepicker { date ->
            fechaPago = date.toString()
            Toast.makeText(context, fechaEmision, Toast.LENGTH_LONG).show()
            //dialogStateNominalOEfectiva.show()
        }
    }


    //Fecha de emision
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        Text(text = "Ingrese la fecha de emision")
        datepicker { date ->
            fechaEmision = date.toString()
            //Toast.makeText(context, fechaEmision, Toast.LENGTH_LONG).show()
            dialogStatePago.show()
        }

    }
//////////////////////////////////////////

    val dialogStateCostosAmount = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateCostosAmount, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        input(label = "Amount", hint = "Ingrese cantidad") { inputString ->
            costo.amount = inputString.toDouble()
            costos.plus(costo)
            Toast.makeText(context, costos.toString(), Toast.LENGTH_LONG).show()
        }
    }


    ////////////////////////
    //Agregar costos inicial o final

    val dialogStateCostos = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogStateCostos, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        listItemsSingleChoice(
            list = listOf("Costo inicial", "Costo final"),
            initialSelection = 0
        ) {
            when (it) {
                0 -> {
                    costo.esInicial = true
                    dialogStateCostosAmount.show()

                }
                1 -> {
                    costo.esInicial = true
                    dialogStateCostosAmount.show()
                }
            }

        }
    }

    ///////////////


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Gray)
            .clip(RoundedCornerShape(25.dp))

    )
    {

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = valueType,
                modifier = Modifier.align(CenterHorizontally)
            )
            Text(
                text = fechaEmision,
                modifier = Modifier.align(CenterHorizontally)
            )
            Text(
                text = fechaPago,
                modifier = Modifier.align(CenterHorizontally)
            )
            Button(
                modifier = Modifier.background(Color.Transparent),
                onClick = { dialogState.show() }) {
                Text(text = "Agregar fechas ")
            }

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = totalFacturado,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                },
                onValueChange = { totalFacturado = it },
                label = { Text(text = "Ingrese total a facturar") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = retencion,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                },
                onValueChange = { retencion = it },
                label = { Text(text = "Ingrese retencion") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = daysPerYear.toString(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null
                    )
                },
                onValueChange = { daysPerYear = it.toInt() },
                label = { Text(text = "Ingrese dias por año") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = plazoTaza,
                leadingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                onValueChange = { plazoTaza = it },
                label = { Text(text = "Ingrese plazo de tasa") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = tasa,
                leadingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                onValueChange = { tasa = it },
                label = { Text(text = "Ingrese tasa") }
            )







            Button(onClick = { dialogStateCostos.show() }) {
                Text(text = "Añadir costos")
            }




            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
                onClick = {
                    val saveDataService = ApiClient.buildCartera()

                    saveDataService?.saveCartera(
                        CarteraApiRequest(
                            "Nominal",
                            true,
                            "" + fechaEmision,
                            "" + fechaPago,
                            totalFacturado.toDouble(),
                            retencion.toDouble(),
                            "365".toInt(),
                            plazoTaza,
                            tasa.toFloat(),
                            periodoCapitaliza,
                            1,
                            1,
                            costos
                        )
                    )?.enqueue(object : Callback<CarteraApiRequest> {
                        override fun onResponse(
                            call: Call<CarteraApiRequest>,
                            response: Response<CarteraApiRequest>
                        ) {
                            if (response.isSuccessful) {
                                Log.d("tttttttttttttttttttttttttttttttttttt", response.toString())
                                Toast.makeText(
                                    context,
                                    "555555555555555555555",
                                    Toast.LENGTH_LONG
                                ).show()
                                Toast.makeText(
                                    context,
                                    response.body().toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                                //Thread.sleep(3_000)
                                navController.navigate(Screen.SplasScreen.route)
                            }
                        }

                        override fun onFailure(call: Call<CarteraApiRequest>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "No se pudo enviar el request, error: $t",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMainActivity", t.toString())
                            navController.navigate(Screen.SplasScreen.route)
                        }

                    })

                }) {
                Text(text = "Registrar")
            }
        }


    }
}










