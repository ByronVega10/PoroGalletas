package com.example.porogalletas.data.platillo

import com.example.porogalletas.data.remote.dto.PlatilloDto
import com.example.porogalletas.data.remote.network.SupabaseService
import com.example.porogalletas.model.Platillo
import com.example.porogalletas.R

class PlatilloRepository {

    private val api = SupabaseService.platillosApi

    suspend fun obtenerPlatillos(): List<Platillo> {
        return api.obtenerPlatillos().map { dto ->
            dto.toPlatillo()
        }
    }

    suspend fun agregarPlatillo(
        nombre: String,
        ingredientes: List<String>,
        precio: Int
    ) {
        api.crearPlatillo(
            PlatilloDto(
                nombre = nombre,
                ingredientes = ingredientes,
                precio = precio
            )
        )
    }

    suspend fun eliminarPlatillo(id: Long) {
        api.eliminarPlatillo("eq.$id")
    }

    suspend fun editarPlatillo(platillo: Platillo) {
        api.actualizarPlatillo(
            id = "eq.${platillo.id}",
            platillo = PlatilloDto(
                nombre = platillo.nombre,
                ingredientes = platillo.ingredientes,
                precio = platillo.precio
            )
        )
    }


    private fun PlatilloDto.toPlatillo(): Platillo {
        return Platillo(
            id = this.id?.toInt() ?: 0,
            nombre = nombre,
            ingredientes = ingredientes.toMutableList(),
            precio = precio,
            imageResId = obtenerImagenPorDefecto(nombre)
        )
    }

    private fun obtenerImagenPorDefecto(nombre: String): Int {
        return when {
            nombre.contains("Vegana", true) -> R.drawable.plato2
            else -> R.drawable.plato1
        }
    }
}