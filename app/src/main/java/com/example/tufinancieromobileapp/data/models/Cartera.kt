package com.example.tufinancieromobileapp.data.models

import java.time.LocalDateTime

class Cartera (
    val valueType: String,
    val esNominal: Boolean,
    val emisionDate: String,
    val paymentDate: String,
    val totalFacturado: Double,
    val retencion: Double,
    val daysPerYear: Int,
    val plazoTaza: String,
    val discountDate: String,
    val periodoCapitaliza: Int,
    val emisor: User,
    val tasa: Float,
    val userReceptorId: Int,
    val costos: List<Costos>,
    val tasaEfectivaAnual: Float,
    val diasTranscurridos: Int,
    val tasaEfectiva: Float,
    val tasaDescontada: Float,
    val discountPerDays: Double,
    val costesInicialesTotales: Double,
    val valorNeto: Long,
    val valorTotalARecibir: Double,
    val costesFinalesTotales: Double,
    val valorTotalAEntregar: Long,
    val tasaCosteEfectivaAnual: Long

        )