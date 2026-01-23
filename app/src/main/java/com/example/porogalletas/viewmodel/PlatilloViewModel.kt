package com.example.porogalletas.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.porogalletas.R
import com.example.porogalletas.model.Platillo

class PlatilloViewModel : ViewModel() {

    private val _platillos = mutableStateListOf<Platillo>()
    val platillos: List<Platillo> = _platillos

    init {
        // Datos de ejemplo (simulan datos del sistema)
        _platillos.addAll(
            listOf(
                Platillo(
                    id = 1,
                    nombre = "Poro Galleta Clásica",
                    ingredientes = mutableListOf("Avena", "Miel", "Chocolate"),
                    precio = 2500,
                    imageResId = R.drawable.plato1
                ),
                Platillo(
                    id = 2,
                    nombre = "Poro Galleta Vegana",
                    ingredientes = mutableListOf("Almendra", "Cacao", "Dátiles"),
                    precio = 2800,
                    imageResId = R.drawable.plato2
                )
            )
        )
    }
    fun agregarPlatillo(platillo: Platillo) {
        _platillos.add(platillo)
    }


    fun eliminarPlatillo(id: Int) {
        _platillos.removeIf { it.id == id }
    }

    fun editarPlatillo(platilloEditado: Platillo) {
        val index = _platillos.indexOfFirst { it.id == platilloEditado.id }
        if (index != -1) {
            _platillos[index] = platilloEditado
        }
    }
}