package com.impulsa.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impulsa.app.data.Asesor
import com.impulsa.app.viewmodel.EstadoSincronizacion
import com.impulsa.app.viewmodel.VentaViewModel

private val FORMAS_PAGO = listOf("Transferencia", "Tarjeta de crédito", "Tarjeta de débito", "Efectivo")

@Composable
fun RegistrarVentaScreen(
    ventaViewModel: VentaViewModel,
    asesor: Asesor? = null,
    alVolver: () -> Unit,
    alGuardar: () -> Unit
) {
    var cliente by remember { mutableStateOf("") }
    var concepto by remember { mutableStateOf("") }
    var valorTexto by remember { mutableStateOf("") }
    var formaPago by remember { mutableStateOf(FORMAS_PAGO.first()) }
    var nota by remember { mutableStateOf("") }
    var errorValidacion by remember { mutableStateOf<String?>(null) }

    val estadoSincronizacion by ventaViewModel.estadoSincronizacion.collectAsState()
    val guardando = estadoSincronizacion == EstadoSincronizacion.SINCRONIZANDO

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            IconButton(onClick = alVolver) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text("Registrar venta", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = cliente,
            onValueChange = { cliente = it },
            label = { Text("Cliente") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = concepto,
            onValueChange = { concepto = it },
            label = { Text("Concepto") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = valorTexto,
            onValueChange = { valorTexto = it.filter { c -> c.isDigit() || c == '.' } },
            label = { Text("Valor") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(14.dp))
        Text("Forma de pago", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(6.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FORMAS_PAGO.forEach { opcion ->
                FilterChip(
                    selected = formaPago == opcion,
                    onClick = { formaPago = opcion },
                    label = { Text(opcion) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = androidx.compose.ui.graphics.Color.White
                    )
                )
            }
        }

        Spacer(Modifier.height(14.dp))
        OutlinedTextField(
            value = nota,
            onValueChange = { nota = it },
            label = { Text("Nota (opcional)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        )

        if (errorValidacion != null) {
            Spacer(Modifier.height(8.dp))
            Text(errorValidacion ?: "", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        val contexto = LocalContext.current

        Spacer(Modifier.height(24.dp))
        Button(
            enabled = !guardando,
            onClick = {
                val valor = valorTexto.toDoubleOrNull()
                if (cliente.isBlank() || concepto.isBlank() || valor == null || valor <= 0.0) {
                    errorValidacion = "Completa cliente, concepto y un valor válido"
                    return@Button
                }
                errorValidacion = null
                ventaViewModel.registrarVenta(
                    cliente = cliente,
                    concepto = concepto,
                    valor = valor,
                    formaPago = formaPago,
                    nota = nota,
                    asesorCorreo = asesor?.correo
                ) { _, sincronizada ->
                    if (sincronizada) {
                        Toast.makeText(contexto, "Venta respaldada en Firebase Firestore con éxito", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(contexto, "Guardada localmente (Firebase rechazó el envío - Revisa las Reglas de Firestore)", Toast.LENGTH_LONG).show()
                    }
                    alGuardar()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(if (guardando) "Guardando..." else "Guardar venta", fontWeight = FontWeight.SemiBold)
        }
    }
}
