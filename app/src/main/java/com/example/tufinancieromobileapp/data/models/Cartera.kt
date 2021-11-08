package com.example.tufinancieromobileapp.data.models

class Cartera (
    val id: Int,
    val fechaEmision: String,
    val fechaPago: String,
    val totalFacturado: Long,
    val retencion: Long,
    val motiv: Int,
    val plazo: String,
    val tasaEfectiva: Float,
    val fechaDescuento: String
        )