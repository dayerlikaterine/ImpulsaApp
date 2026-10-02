package com.impulsa.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.impulsa.app.data.Asesor
import com.impulsa.app.data.AsesorDemo
import com.impulsa.app.ui.components.AvatarIniciales
import com.impulsa.app.ui.components.TarjetaImpulsa
import com.impulsa.app.ui.theme.ImpulsaTheme

@Composable
fun PerfilScreen(
    asesor: Asesor,
    alVerCreditos: () -> Unit,
    alCerrarSesion: () -> Unit
) {
    var mostrarConfirmacion by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text("Perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))

        TarjetaImpulsa(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarIniciales(asesor.iniciales, tamano = 56)
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(asesor.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(asesor.correo, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(asesor.telefono, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        OpcionPerfil(texto = "Descripción de la app y créditos", onClick = alVerCreditos)

        Spacer(Modifier.height(28.dp))

        OutlinedButton(
            onClick = { mostrarConfirmacion = true },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Filled.Logout, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Cerrar sesión")
        }
    }

    if (mostrarConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacion = false },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Seguro que quieres salir de tu cuenta?") },
            confirmButton = {
                TextButton(onClick = {
                    mostrarConfirmacion = false
                    alCerrarSesion()
                }) { Text("Cerrar sesión", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = {
                TextButton(onClick = { mostrarConfirmacion = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
private fun OpcionPerfil(texto: String, onClick: () -> Unit) {
    TarjetaImpulsa(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(texto, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    ImpulsaTheme {
        PerfilScreen(
            asesor = AsesorDemo.PERFIL,
            alVerCreditos = {},
            alCerrarSesion = {}
        )
    }
}
