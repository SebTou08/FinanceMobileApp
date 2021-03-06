package com.example.tufinancieromobileapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.data.remote.interfaces.ApiClient
import com.example.tufinancieromobileapp.screens.Navigation
import com.example.tufinancieromobileapp.ui.theme.TuFinancieroMobileAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    var carteras by mutableStateOf(listOf<Cartera>())

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCarteras()
        setContent {
            TuFinancieroMobileAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(_carteras = carteras)
                }
            }
        }
    }


    private fun loadCarteras() {
        val carterasInterface = ApiClient.buildCartera()
        val fetchCartera = carterasInterface?.fetchCartera()

        fetchCartera?.enqueue(object : Callback<List<Cartera>> {


            override fun onResponse(call: Call<List<Cartera>>, response: Response<List<Cartera>>) {
                carteras = response.body()!!
            }

            override fun onFailure(call: Call<List<Cartera>>, t: Throwable) {
                Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMainActivity", t.toString())
            }

        }
        )
    }
}





