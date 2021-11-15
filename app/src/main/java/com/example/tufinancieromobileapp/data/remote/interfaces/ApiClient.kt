package com.example.tufinancieromobileapp.data.remote.interfaces

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //private const val API_BASE_URL = "https://60c2e09f917002001739da47.mockapi.io/"
    private const val API_BASE_URL = "https://tufinancierpo.azurewebsites.net/api/"

    var carteraInterface: CarteraInterface?=null

    fun buildCartera(): CarteraInterface?{
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        var retrofit: Retrofit = builder.build()
        carteraInterface = retrofit.create(CarteraInterface::class.java)
        return carteraInterface as CarteraInterface
    }


}