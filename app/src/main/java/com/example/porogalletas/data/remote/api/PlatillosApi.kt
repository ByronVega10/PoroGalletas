package com.example.porogalletas.data.remote.api

import com.example.porogalletas.data.remote.dto.PlatilloDto
import retrofit2.http.*

interface PlatillosApi {

    @GET("platillos")
    suspend fun obtenerPlatillos(): List<PlatilloDto>

    @POST("platillos")
    suspend fun crearPlatillo(
        @Body platillo: PlatilloDto
    )

    @PATCH("platillos")
    suspend fun actualizarPlatillo(
        @Query("id") id: String,
        @Body platillo: PlatilloDto
    )

    @DELETE("platillos")
    suspend fun eliminarPlatillo(
        @Query("id") id: String
    )
}