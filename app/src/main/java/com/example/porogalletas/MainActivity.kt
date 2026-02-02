package com.example.porogalletas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.porogalletas.navigation.AppNavigation
import com.example.porogalletas.ui.theme.PoroGalletasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = remember { mutableStateOf(false) }  // Estado simple con remember

            PoroGalletasTheme(
                darkTheme = isDarkTheme.value  // Pasa el valor booleano
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        isDarkTheme = isDarkTheme.value,
                        onThemeToggle = { isDarkTheme.value = !isDarkTheme.value }
                    )
                }
            }
        }
    }
}
