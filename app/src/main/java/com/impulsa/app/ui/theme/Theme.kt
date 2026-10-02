package com.impulsa.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EsquemaClaro = lightColorScheme(
    primary = ImpulsaMorado,
    onPrimary = Color.White,
    primaryContainer = ImpulsaMoradoClaro,
    onPrimaryContainer = ImpulsaMoradoOscuro,
    secondary = ImpulsaAzulCian,
    onSecondary = Color.White,
    secondaryContainer = ImpulsaAzulClaro,
    onSecondaryContainer = ImpulsaAzulOscuro,
    tertiary = ImpulsaMagenta,
    onTertiary = Color.White,
    tertiaryContainer = ImpulsaRosaClaro,
    onTertiaryContainer = ImpulsaMagentaOscuro,
    background = ImpulsaBg,
    onBackground = ImpulsaTextoOscuro,
    surface = ImpulsaCard,
    onSurface = ImpulsaTextoOscuro,
    surfaceVariant = ImpulsaSurfaceVariant,
    onSurfaceVariant = ImpulsaTextoMedio,
    outline = ImpulsaBorder,
    error = ImpulsaRed
)

private val EsquemaOscuro = darkColorScheme(
    primary = ImpulsaMagenta,
    onPrimary = Color.White,
    primaryContainer = ImpulsaMoradoOscuro,
    onPrimaryContainer = ImpulsaMoradoClaro,
    secondary = ImpulsaAzulCian,
    onSecondary = Color.White,
    tertiary = ImpulsaMorado,
    background = ImpulsaTextoOscuro,
    onBackground = Color.White,
    surface = Color(0xFF241348),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF321E5C),
    onSurfaceVariant = Color(0xFFD3C8E8),
    outline = Color(0xFF4A3678),
    error = ImpulsaRed
)

@Composable
fun ImpulsaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) EsquemaOscuro else EsquemaClaro,
        typography = Tipografia,
        content = content
    )
}
