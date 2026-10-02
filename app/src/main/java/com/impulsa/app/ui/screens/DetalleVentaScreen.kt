package com.impulsa.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impulsa.app.data.VentaEntity
import com.impulsa.app.ui.components.InsigniaImpulsa
import com.impulsa.app.ui.components.TarjetaImpulsa
import com.impulsa.app.viewmodel.VentaViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetalleVentaScreen(
    ventaId: Long,
    ventaViewModel: VentaViewModel,
    alVolver: () -> Unit
) {
    var venta by remember { mutableStateOf<VentaEntity?>(null) }

    LaunchedEffect(ventaId) {
        venta = ventaViewModel.obtenerPorId(ventaId)
    }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            IconButton(onClick = alVolver) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text("Detalle de venta", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        val ventaActual = venta
        if (ventaActual == null) {
            Text("Cargando...", color = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
            TarjetaImpulsa(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(ventaActual.valor),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(4.dp))
                    InsigniaImpulsa(if (ventaActual.sincronizada) "Sincronizada" else "Solo local")

                    Spacer(Modifier.height(18.dp))
                    FilaDato("Cliente", ventaActual.cliente)
                    FilaDato("Concepto", ventaActual.concepto)
                    FilaDato("Forma de pago", ventaActual.formaPago)
                    FilaDato(
                        "Fecha",
                        SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale("es", "CO")).format(Date(ventaActual.fecha))
                    )
                    if (!ventaActual.nota.isNullOrBlank()) {
                        FilaDato("Nota", ventaActual.nota)
                    }
                }
            }
        }
    }
}

@Composable
private fun FilaDato(etiqueta: String, valor: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(etiqueta, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(valor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}
