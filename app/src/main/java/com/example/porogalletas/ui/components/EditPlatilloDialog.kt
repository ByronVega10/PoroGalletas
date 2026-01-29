package com.example.porogalletas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.porogalletas.model.Platillo

@Composable
fun EditPlatilloDialog(
    platillo: Platillo,
    onDismiss: () -> Unit,
    onGuardar: (Platillo) -> Unit
) {
    var nombre by remember { mutableStateOf(platillo.nombre) }
    var ingredientes by remember {
        mutableStateOf(platillo.ingredientes.joinToString(", "))
    }
    var precio by remember { mutableStateOf(platillo.precio.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                val precioInt = precio.toIntOrNull() ?: return@Button

                onGuardar(
                    platillo.copy(
                        nombre = nombre,
                        ingredientes = ingredientes.split(",").map { it.trim() }.toMutableList(),
                        precio = precioInt
                    )
                )
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
            }
        }
    )
}