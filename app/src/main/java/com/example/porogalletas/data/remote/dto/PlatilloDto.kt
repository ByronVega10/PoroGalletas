package com.example.porogalletas.data.remote.dto

data class PlatilloDto(
    val id: Long? = null,
    val nombre: String,
    val ingredientes: List<String>,
    val precio: Int
)