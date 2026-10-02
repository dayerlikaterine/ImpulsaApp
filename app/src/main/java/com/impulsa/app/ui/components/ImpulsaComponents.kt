package com.impulsa.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.impulsa.app.navigation.ImpulsaDestinations
import com.impulsa.app.ui.theme.ImpulsaBorder

/** Tarjeta con el mismo borde/sombra suave que las Card del Figma. */
@Composable
fun TarjetaImpulsa(
    modifier: Modifier = Modifier,
    contenido: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, ImpulsaBorder),
        tonalElevation = 1.dp
    ) {
        Box(modifier = Modifier.padding(16.dp)) { contenido() }
    }
}

/** Insignia de texto con fondo suave, igual al componente "Badge" del Figma. */
@Composable
fun InsigniaImpulsa(texto: String, color: Color = MaterialTheme.colorScheme.primary) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = color.copy(alpha = 0.12f)
    ) {
        Text(
            text = texto,
            color = color,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )
    }
}

/** Iniciales dentro de un círculo, igual al "Avatar" del Figma. */
@Composable
fun AvatarIniciales(iniciales: String, tamano: Int = 44, color: Color = MaterialTheme.colorScheme.primary) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.14f))
            .border(1.5.dp, color.copy(alpha = 0.3f), CircleShape)
            .padding((tamano / 6).dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = iniciales,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = (tamano / 3).sp
        )
    }
}

/** Barra de progreso delgada, igual al "Progress" del Figma. */
@Composable
fun BarraProgreso(valorActual: Float, color: Color = MaterialTheme.colorScheme.primary) {
    LinearProgressIndicator(
        progress = { valorActual.coerceIn(0f, 1f) },
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp)),
        color = color,
        trackColor = ImpulsaBorder
    )
}

data class ItemBarraInferior(val ruta: String, val etiqueta: String, val icono: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun BarraInferiorImpulsa(navController: NavHostController, items: List<ItemBarraInferior>) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        items.forEach { item ->
            val seleccionado = rutaActual?.hierarchy?.any { it.route == item.ruta } == true
            NavigationBarItem(
                selected = seleccionado,
                onClick = {
                    if (!seleccionado) {
                        navController.navigate(item.ruta) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icono, contentDescription = item.etiqueta) },
                label = { Text(item.etiqueta) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

fun rutaTieneBarraInferior(ruta: String?): Boolean = ruta in ImpulsaDestinations.conBarraInferior
