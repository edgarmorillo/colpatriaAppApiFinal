package com.example.prueba.data.model

data class BINResponse(
    val success: Boolean,
    val code: Int,
    val BIN: BinInfo
)

data class BinInfo(
    val valid: Boolean,
    val number: Int,
    val length: Int,
    val scheme: String,
    val brand: String,
    val type: String,
    val level: String,
    val currency: String,
    val issuer: IssuerInfo,
    val country: CountryInfo
)

data class IssuerInfo(
    val name: String,
    val website: String,
    val phone: String
)

data class CountryInfo(
    val country: String,
    val numeric: String,
    val capital: String,
    val idd: String,
    val alpha2: String,
    val alpha3: String,
    val language: String,
    val language_code: String,
    val latitude: Double,
    val longitude: Double
)
