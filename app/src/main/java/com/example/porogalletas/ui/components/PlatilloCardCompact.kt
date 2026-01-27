package com.example.porogalletas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.porogalletas.model.Platillo

@Composable
fun PlatilloCardCompact(
    platillo: Platillo
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Imagen
            Image(
                painter = painterResource(id = platillo.imageResId),
                contentDescription = platillo.nombre,
                modifier = Modifier
                    .size(70.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            // Nombre + ingredientes
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = platillo.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )

                Text(
                    text = platillo.ingredientes.take(3).joinToString(", "),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }

            // Precio
            Text(
                text = "$${platillo.precio}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}