package com.example.porogalletas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.porogalletas.data.datastore.SessionManager
import com.example.porogalletas.data.usuario.UsuarioRepository
import com.example.porogalletas.model.UsuarioErrores
import com.example.porogalletas.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel (
    private val repository: UsuarioRepository,
    private val sessionManager: SessionManager
): ViewModel() {

    private val _estado = MutableStateFlow(value = Usuario())
    val estado: StateFlow<Usuario> = _estado

    // Actualiza el campo nombre
    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    // Actualiza el campo correo
    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    // Actualiza el campo clave
    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo inválido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }

    fun login(onSuccess: () -> Unit) {
        if (!validarFormulario()) return

        viewModelScope.launch {
            val usuario = repository.login(
                correo = estado.value.correo,
                clave = estado.value.clave
            )

            if (usuario == null) {
                _estado.update {
                    it.copy(
                        errores = it.errores.copy(
                            general = "Correo o contraseña incorrectos"
                        )
                    )
                }
            } else {
                sessionManager.saveSession(usuario.correo)
                onSuccess()
            }
        }
    }

    fun logout(onLogout: () -> Unit) {
        viewModelScope.launch {
            sessionManager.clearSession()
            onLogout()
        }
    }
}