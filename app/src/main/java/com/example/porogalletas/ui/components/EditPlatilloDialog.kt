package com.example.porogalletas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.porogalletas.model.Platillo
import com.example.porogalletas.viewmodel.PlatilloViewModel

@Composable
fun EditPlatilloDialog(
    platillo: Platillo,
    platilloViewModel: PlatilloViewModel,
    onDismiss: () -> Unit,
    onGuardar: (Platillo) -> Unit
) {
    var nombre by remember { mutableStateOf(platillo.nombre) }
    var ingredientes by remember {
        mutableStateOf(platillo.ingredientes.joinToString(", "))
    }
    var precio by remember { mutableStateOf(platillo.precio.toString()) }

    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                val precioInt = precio.toIntOrNull()

                when {
                    nombre.isBlank() -> {
                        error = "El nombre no puede estar vacío"
                    }

                    !platilloViewModel.nombreEsValidoParaEditar(nombre, platillo.id) -> {
                        error = "El nombre ya está en uso, prueba con otro"
                    }

                    precioInt == null || precioInt <= 0 -> {
                        error = "Precio inválido"
                    }

                    else -> {
                        onGuardar(
                            platillo.copy(
                                nombre = nombre.trim(),
                                ingredientes = ingredientes
                                    .split(",")
                                    .map { it.trim() }
                                    .toMutableList(),
                                precio = precioInt
                            )
                        )
                    }
                }
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = {
            Text("Editar Platillo")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )

                OutlinedTextField(
                    value = ingredientes,
                    onValueChange = { ingredientes = it },
                    label = { Text("Ingredientes") }
                )

                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") }
                )

                error?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    )
}