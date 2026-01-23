package com.example.porogalletas.data.platillo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platillos")
data class PlatilloEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val ingredientes: String,
    val precio: Int,
    val imagenRes: Int
)