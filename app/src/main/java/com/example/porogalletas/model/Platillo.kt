package com.example.porogalletas.model

data class Platillo(
    val id: Int,
    val nombre: String,                         // Nombre del platillo
    val ingredientes: MutableList<String>,      // Lista de Ingredientes
    val precio: Int,                            // Precio del Platillo
    val imageResId: Int                         // Id de Imgane
)