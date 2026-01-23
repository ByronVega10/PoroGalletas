package com.example.porogalletas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.porogalletas.model.Platillo

@Composable
fun PlatilloCard(platillo: Platillo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(id = platillo.imageResId),
                contentDescription = platillo.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = platillo.nombre,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = platillo.ingredientes.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}