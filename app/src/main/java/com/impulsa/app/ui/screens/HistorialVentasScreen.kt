package com.impulsa.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

private fun formatoMoneda(valor: Double): String =
    NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(valor)

private fun formatoFecha(fechaMillis: Long): String =
    SimpleDateFormat("dd MMM yyyy", Locale("es", "CO")).format(Date(fechaMillis))

@Composable
fun HistorialVentasScreen(
    ventaViewModel: VentaViewModel,
    alSeleccionarVenta: (Long) -> Unit
) {
    val ventas by ventaViewModel.ventas.collectAsState()
    var busqueda by remember { mutableStateOf("") }

    val ventasFiltradas = remember(ventas, busqueda) {
        if (busqueda.isBlank()) ventas
        else ventas.filter { it.cliente.contains(busqueda, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text("Historial de ventas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(14.dp))

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            placeholder = { Text("Buscar por cliente") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(14.dp))

        if (ventasFiltradas.isEmpty()) {
            Text(
                "No hay ventas para mostrar.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(ventasFiltradas, key = { it.id }) { venta ->
                    FilaVenta(venta = venta, onClick = { alSeleccionarVenta(venta.id) })
                }
            }
        }
    }
}

@Composable
private fun FilaVenta(venta: VentaEntity, onClick: () -> Unit) {
    TarjetaImpulsa(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(venta.cliente, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text(venta.concepto, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(4.dp))
                InsigniaImpulsa(formatoFecha(venta.fecha))
            }
            Text(formatoMoneda(venta.valor), fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}
