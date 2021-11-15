package com.example.tufinancieromobileapp.data.remote.interfaces

import com.example.tufinancieromobileapp.data.models.Cartera
import com.example.tufinancieromobileapp.data.models.CarteraApiRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarteraInterface {

    @GET("document")
    fun fetchCartera(): Call<List<Cartera>>

    @POST("document/userid/1")
    fun saveCartera (@Body body: CarteraApiRequest): Call<CarteraApiRequest>
}