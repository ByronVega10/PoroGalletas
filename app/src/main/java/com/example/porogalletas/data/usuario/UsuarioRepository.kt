package com.example.porogalletas.data.usuario

import com.example.porogalletas.data.remote.api.UsuarioApi
import com.example.porogalletas.data.remote.dto.UsuarioDto

class UsuarioRepository(
    private val api: UsuarioApi
) {

    suspend fun login(correo: String, clave: String): UsuarioDto? {
        val usuarios = api.login(
            correo = "eq.$correo",
            clave = "eq.$clave"
        )

        return usuarios.firstOrNull()
    }

    suspend fun registrar(nombre: String, correo: String, clave: String) {
        val usuario = UsuarioDto(
            nombre = nombre,
            correo = correo,
            clave = clave
        )
        api.registrarUsuario(usuario)
    }
}