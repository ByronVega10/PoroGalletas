package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.porogalletas.viewmodel.UsuarioViewModel
import com.example.porogalletas.ui.components.PlatilloCard
import com.example.porogalletas.viewmodel.PlatilloViewModel


@Composable
fun HomeScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    platilloViewModel: PlatilloViewModel

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Bienvenido a PoroGalletas",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            platilloViewModel.platillos.forEach { platillo ->
                PlatilloCard(platillo)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }