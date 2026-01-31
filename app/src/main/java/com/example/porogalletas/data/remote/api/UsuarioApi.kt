package com.example.porogalletas.data.remote.api

import com.example.porogalletas.data.remote.dto.UsuarioDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioApi {

    @GET("usuarios")
    suspend fun login(
        @Query("select") select: String = "*",
        @Query("correo") correo: String,
        @Query("clave") clave: String
    ): List<UsuarioDto>

    @POST("usuarios")
    suspend fun registrarUsuario(
        @Body usuario: UsuarioDto
    )
}