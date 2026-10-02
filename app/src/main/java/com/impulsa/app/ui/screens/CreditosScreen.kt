package com.impulsa.app.ui.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impulsa.app.R
import com.impulsa.app.ui.components.AvatarIniciales
import com.impulsa.app.ui.components.TarjetaImpulsa

private val ESTUDIANTES = listOf(
    "Maria José Peña Anacona",
    "Daniel Esteban Arciniegas Barrera",
    "Dayerli Katerine Tamayo Solarte"
)

@Composable
fun CreditosScreen(alVolver: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = alVolver) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text("Descripción y créditos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        TarjetaImpulsa(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.impulsa),
                    contentDescription = "Logo Impulsa",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Impulsa es una app para que un asesor de ventas registre sus ventas, " +
                        "consulte su historial y siga su progreso hacia la meta mensual de comisión. " +
                        "Las ventas se guardan en el dispositivo y se respaldan en línea con Firebase.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(10.dp))
                Text("Versión 1.0", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Desarrollado por", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(10.dp))

        ESTUDIANTES.forEach { nombre ->
            TarjetaImpulsa(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AvatarIniciales(
                        iniciales = nombre.split(" ").take(2).joinToString("") { it.take(1) },
                        tamano = 40
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(nombre, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
