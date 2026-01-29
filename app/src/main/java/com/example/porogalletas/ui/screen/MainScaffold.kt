package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.porogalletas.viewmodel.PlatilloViewModel
import com.example.porogalletas.viewmodel.UsuarioViewModel

@Composable
fun MainScaffold(
    parentNavController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    platilloViewModel: PlatilloViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(72.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar",
                        tint = Color.White
                    )
                    Text(
                        text = "Agregar",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp,
                modifier = Modifier.height(70.dp),
                actions = {
                    // HOME
                    IconButton(
                        onClick = { navController.navigate("home") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home",
                                tint = if (currentRoute == "home")
                                    MaterialTheme.colorScheme.primary
                                else Color.Gray
                            )
                            Text(
                                text = "Home",
                                fontSize = 12.sp,
                                color = if (currentRoute == "homeScreen")
                                    MaterialTheme.colorScheme.primary
                                else Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // EDIT
                    IconButton(
                        onClick = { navController.navigate("edit") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = if (currentRoute == "edit")
                                    MaterialTheme.colorScheme.primary
                                else Color.Gray
                            )
                            Text(
                                text = "Editar",
                                fontSize = 12.sp,
                                color = if (currentRoute == "Edit")
                                    MaterialTheme.colorScheme.primary
                                else Color.Gray
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") { HomeScreen(parentNavController, usuarioViewModel, platilloViewModel) }
                composable("edit") { EditScreen() }
                composable("add") { AddScreen(navController = navController,
                                                      platilloViewModel = platilloViewModel) }
            }
        }
    }
}