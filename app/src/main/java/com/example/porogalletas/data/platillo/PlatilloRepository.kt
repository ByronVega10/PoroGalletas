package com.example.porogalletas.data.platillo

class PlatilloRepository(private val platilloDao: PlatilloDao) {

    suspend fun insertarPlatillos(platillos: List<PlatilloEntity>): List<Long> {
        return platilloDao.insertarPlatillos(platillos)
    }

    suspend fun obtenerPlatillos(): List<PlatilloEntity> {
        return platilloDao.obtenerPlatillos()
    }
}