package com.impulsa.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impulsa.app.R
import com.impulsa.app.data.Asesor
import com.impulsa.app.ui.components.AvatarIniciales
import com.impulsa.app.ui.components.BarraProgreso
import com.impulsa.app.ui.components.TarjetaImpulsa
import com.impulsa.app.viewmodel.VentaViewModel
import java.text.NumberFormat
import java.util.Locale

private fun formatoMoneda(valor: Double): String =
    NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(valor)

@Composable
fun DashboardScreen(
    asesor: Asesor,
    ventaViewModel: VentaViewModel,
    alRegistrarVenta: () -> Unit
) {
    val ventas by ventaViewModel.ventas.collectAsState()
    val comision by ventaViewModel.comisionAcumulada.collectAsState()
    val progreso = ventaViewModel.progresoMeta(asesor.metaMensual)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarIniciales(asesor.iniciales)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Hola, ${asesor.nombre.substringBefore(" ")}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Este es tu resumen de hoy", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Image(
                painter = painterResource(id = R.drawable.impulsa),
                contentDescription = "Logo Impulsa",
                modifier = Modifier.height(44.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TarjetaImpulsa(modifier = Modifier.weight(1f)) {
                Column {
                    Text("Ventas", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("${ventas.size}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
            TarjetaImpulsa(modifier = Modifier.weight(1f)) {
                Column {
                    Text("Comisión", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(formatoMoneda(comision), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        TarjetaImpulsa(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Meta mensual", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Text("${(progreso * 100).toInt()}%", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(8.dp))
                BarraProgreso(progreso)
                Spacer(Modifier.height(6.dp))
                Text(
                    "${formatoMoneda(comision)} de ${formatoMoneda(asesor.metaMensual)}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = alRegistrarVenta,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Registrar venta", fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(24.dp))
        Text("Últimas ventas", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))

        if (ventas.isEmpty()) {
            Text(
                "Todavía no has registrado ventas.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            ventas.take(3).forEach { venta ->
                TarjetaImpulsa(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(venta.cliente, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            Text(venta.concepto, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Text(formatoMoneda(venta.valor), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
