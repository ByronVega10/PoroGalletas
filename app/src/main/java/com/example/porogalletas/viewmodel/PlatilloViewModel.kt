package com.example.porogalletas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateListOf
import com.example.porogalletas.data.platillo.PlatilloRepository
import com.example.porogalletas.model.Platillo
import kotlinx.coroutines.launch

class PlatilloViewModel : ViewModel() {

    private val repository = PlatilloRepository()

    private val _platillos = mutableStateListOf<Platillo>()
    val platillos: List<Platillo> = _platillos

    init {
        cargarPlatillos()
    }

    private fun cargarPlatillos() {
        viewModelScope.launch {
            try {
                _platillos.clear()
                _platillos.addAll(repository.obtenerPlatillos())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun agregarPlatillo(
        nombre: String,
        ingredientesTexto: String,
        precio: Int
    ) {
        val ingredientes = ingredientesTexto
            .split(",")
            .map { it.trim() }

        viewModelScope.launch {
            repository.agregarPlatillo(nombre, ingredientes, precio)
            cargarPlatillos()
        }
    }

    fun eliminarPlatillo(id: Int) {
        viewModelScope.launch {
            repository.eliminarPlatillo(id.toLong())
            cargarPlatillos()
        }
    }

    fun editarPlatillo(platillo: Platillo) {
        viewModelScope.launch {
            repository.editarPlatillo(platillo)
            cargarPlatillos()
        }
    }
}