package com.es.adatBlogNoticias.model

data class Address(
    val streetName: String,
    val houseNumber: String,
    val unitLetter: String?,
    val zipCode: String,
    val city: String
)