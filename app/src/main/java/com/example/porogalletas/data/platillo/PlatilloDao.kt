package com.example.porogalletas.data.platillo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
@JvmSuppressWildcards
interface PlatilloDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPlatillos(platillos: List<PlatilloEntity>): List<Long>

    @Query("SELECT * FROM platillos")
    suspend fun obtenerPlatillos(): List<PlatilloEntity>
}