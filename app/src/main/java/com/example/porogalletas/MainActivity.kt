package com.example.porogalletas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.porogalletas.navigation.AppNavigation
import com.example.porogalletas.ui.theme.PoroGalletasTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }  // ← NUEVO

            PoroGalletasTheme(
                darkTheme = isDarkTheme  // ← CAMBIO: pasa el estado
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        onDarkThemeChanged = { newValue -> isDarkTheme = newValue }  // ← NUEVO callback
                    )
                }
            }
        }
    }
}