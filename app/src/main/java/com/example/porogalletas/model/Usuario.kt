package com.example.porogalletas.model

data class Usuario(
    val nombre: String = "",                        // Nombre del usuario
    val correo: String = "",                        // Correo electrónico
    val clave: String = "",                         // Contraseña
    val errores: UsuarioErrores = UsuarioErrores()  // Objeto que contiene los errores por campo
)