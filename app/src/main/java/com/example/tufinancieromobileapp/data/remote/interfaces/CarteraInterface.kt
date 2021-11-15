package com.example.tufinancieromobileapp.data.remote.interfaces

import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.data.models.dtoPage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarteraInterface {

    @GET("document")
    fun fetchCartera(): Call<dtoPage>

    @POST("cartera")
    fun saveCartera (@Body body: Cartera): Call<Cartera>
}