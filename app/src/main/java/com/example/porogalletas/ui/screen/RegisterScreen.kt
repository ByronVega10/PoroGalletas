package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.porogalletas.viewmodel.UsuarioViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
){
    val estado by usuarioViewModel.estado.collectAsState()


    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ){

        // Campo nombre
        OutlinedTextField(
            value = estado.nombre,
            onValueChange = usuarioViewModel::onNombreChange,
            label = { Text(text = "Nombre") },
            isError = estado.errores.nombre != null,
            supportingText = {
                estado.errores.nombre?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo correo
        OutlinedTextField(
            value = estado.correo,
            onValueChange = usuarioViewModel::onCorreoChange,
            label = { Text("Correo") },
            isError = estado.errores.correo != null,
            supportingText = {
                estado.errores.correo?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo clave
        OutlinedTextField(
            value = estado.clave,
            onValueChange = usuarioViewModel::onClaveChange,
            label = { Text(text = "Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = estado.errores.clave != null,
            supportingText = {
                estado.errores.clave?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        estado.errores.general?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Botón: enviar
        Button(onClick = {
            usuarioViewModel.login {
                navController.navigate("Home") {
                    popUpTo("Login") { inclusive = true }
                }
            }
        }) {
            Text("Ingresar")
        }
    }
}