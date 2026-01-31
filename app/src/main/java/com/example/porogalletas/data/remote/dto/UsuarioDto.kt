package com.example.porogalletas.data.remote.dto



data class UsuarioDto(
    val id: String? = null,
    val nombre: String,
    val correo: String,
    val clave: String
)