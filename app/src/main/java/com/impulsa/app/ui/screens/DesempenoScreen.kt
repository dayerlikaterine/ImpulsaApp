package com.impulsa.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impulsa.app.data.Asesor
import com.impulsa.app.ui.components.BarraProgreso
import com.impulsa.app.ui.components.TarjetaImpulsa
import com.impulsa.app.viewmodel.VentaViewModel
import java.text.NumberFormat
import java.util.Locale

private fun formatoMoneda(valor: Double): String =
    NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(valor)

@Composable
fun DesempenoScreen(asesor: Asesor, ventaViewModel: VentaViewModel) {
    val ventas by ventaViewModel.ventas.collectAsState()
    val total by ventaViewModel.totalVendido.collectAsState()
    val comision by ventaViewModel.comisionAcumulada.collectAsState()
    val progreso = ventaViewModel.progresoMeta(asesor.metaMensual)

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text("Mi desempeño", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        TarjetaImpulsa(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Progreso hacia la meta", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(10.dp))
                BarraProgreso(progreso)
                Spacer(Modifier.height(6.dp))
                Text(
                    "${formatoMoneda(comision)} de ${formatoMoneda(asesor.metaMensual)} (${(progreso * 100).toInt()}%)",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        Row {
            TarjetaImpulsa(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column {
                    Text("Total vendido", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(formatoMoneda(total), fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.width(12.dp))
            TarjetaImpulsa(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column {
                    Text("Nº de ventas", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("${ventas.size}", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        TarjetaImpulsa(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Comisión acumulada", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(formatoMoneda(comision), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("8% sobre el total vendido", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
