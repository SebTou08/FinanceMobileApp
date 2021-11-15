package com.example.tufinancieromobileapp.data.models

class CarteraApiRequest (
    val valueType: String,
    val esNominal: Boolean,
    val emisionDate: String,
    val paymentDate: String,
    val totalFacturado: Double,
    val retencion: Double,
    val daysPerYear: Int,
    val plazoTaza: String,
    val tasa: Float,
    val periodoCapitaliza: Int,
    val receptorId: Int,
    val userReceptorId: Int,
    val costos: List<Costos>,
        )