package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.porogalletas.viewmodel.PlatilloViewModel

@Composable
fun AddScreen(
    navController: NavHostController,
    platilloViewModel: PlatilloViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Agregar Platillo",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del platillo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ingredientes,
            onValueChange = { ingredientes = it },
            label = { Text("Ingredientes (separados por coma)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val precioInt = precio.toIntOrNull()

                when {
                    nombre.isBlank() ->
                        error = "El nombre no puede estar vacío"

                    ingredientes.isBlank() ->
                        error = "Debes ingresar ingredientes"

                    precioInt == null || precioInt <= 0 ->
                        error = "Precio inválido"

                    else -> {
                        platilloViewModel.agregarPlatillo(
                            nombre = nombre,
                            ingredientesTexto = ingredientes,
                            precio = precioInt
                        )
                        navController.popBackStack()
                    }
                }
            }
        ) {
            Text("Guardar Platillo")
        }
    }
}