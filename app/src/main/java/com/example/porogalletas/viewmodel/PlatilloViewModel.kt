package com.example.porogalletas.viewmodel

import androidx.lifecycle.ViewModel
import com.example.porogalletas.model.Platillo

class PlatilloViewModel : ViewModel() {

    private val platillos = mutableListOf<Platillo>()

    fun agregarPlatillo(platillo: Platillo) {
        platillos.add(platillo)
    }

    fun obtenerPlatillos(): List<Platillo> {
        return platillos
    }

    fun eliminarPlatillo(id: Int) {
        platillos.removeIf { it.id == id }
    }

    fun editarPlatillo(platilloEditado: Platillo) {
        val index = platillos.indexOfFirst { it.id == platilloEditado.id }
        if (index != -1) {
            platillos[index] = platilloEditado
        }
    }
}