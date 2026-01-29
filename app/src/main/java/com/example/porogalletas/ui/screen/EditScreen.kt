package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.porogalletas.model.Platillo
import com.example.porogalletas.ui.components.EditPlatilloDialog
import com.example.porogalletas.ui.components.PlatilloEditableCard
import com.example.porogalletas.viewmodel.PlatilloViewModel

@Composable
fun EditScreen(
    platilloViewModel: PlatilloViewModel
) {
    var platilloSeleccionado by remember { mutableStateOf<Platillo?>(null) }
    var platilloAEliminar by remember { mutableStateOf<Platillo?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Editar / Eliminar Platillos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(platilloViewModel.platillos) { platillo ->
                PlatilloEditableCard(
                    platillo = platillo,
                    onEditar = { platilloSeleccionado = it },
                    onEliminar = { platilloAEliminar = it }
                )
            }
        }
    }

    platilloSeleccionado?.let {
        EditPlatilloDialog(
            platillo = it,
            platilloViewModel = platilloViewModel,
            onDismiss = { platilloSeleccionado = null },
            onGuardar = { platilloEditado ->
                platilloViewModel.editarPlatillo(platilloEditado)
                platilloSeleccionado = null
            }
        )
    }

    platilloAEliminar?.let { platillo ->
        AlertDialog(
            onDismissRequest = { platilloAEliminar = null },
            title = {
                Text("Eliminar platillo")
            },
            text = {
                Text("¿Estás seguro de que deseas eliminar \"${platillo.nombre}\"?")
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    onClick = {
                        platilloViewModel.eliminarPlatillo(platillo.id)
                        platilloAEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { platilloAEliminar = null }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}