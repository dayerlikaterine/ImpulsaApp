package com.impulsa.app.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Paleta extraída directamente del logo oficial de la marca Impulsa
val ImpulsaMagenta = Color(0xFFEC1398)
val ImpulsaMagentaOscuro = Color(0xFFC20078)
val ImpulsaRosaClaro = Color(0xFFFCE4EC)

val ImpulsaMorado = Color(0xFF7C3AED)
val ImpulsaMoradoOscuro = Color(0xFF5B21B6)
val ImpulsaMoradoClaro = Color(0xFFF3E8FF)

val ImpulsaAzulCian = Color(0xFF00B0FF)
val ImpulsaAzulOscuro = Color(0xFF0288D1)
val ImpulsaAzulClaro = Color(0xFFE0F2FE)

val ImpulsaTextoOscuro = Color(0xFF1A0938)
val ImpulsaTextoMedio = Color(0xFF5C4E78)
val ImpulsaTextoMuted = Color(0xFF8C7DA8)

val ImpulsaBg = Color(0xFFF9F7FC)
val ImpulsaCard = Color(0xFFFFFFFF)
val ImpulsaBorder = Color(0xFFE8E0F4)
val ImpulsaSurfaceVariant = Color(0xFFF1EBF9)

// Colores legacy/utilitarios
val ImpulsaTeal = ImpulsaMorado
val ImpulsaTealDark = ImpulsaMoradoOscuro
val ImpulsaTealLight = ImpulsaMoradoClaro
val ImpulsaNavy = ImpulsaTextoOscuro
val ImpulsaSlate = Color(0xFF2E1A4A)
val ImpulsaMid = ImpulsaTextoMedio
val ImpulsaMuted = ImpulsaTextoMuted
val ImpulsaLight = ImpulsaSurfaceVariant

val ImpulsaAmber = Color(0xFFF59E0B)
val ImpulsaRed = Color(0xFFEF4444)
val ImpulsaGreen = Color(0xFF10B981)

// Gradiente oficial de la marca Impulsa (Rosa -> Morado -> Azul Cian)
val GradienteImpulsa = Brush.horizontalGradient(
    colors = listOf(
        ImpulsaMagenta,
        ImpulsaMorado,
        ImpulsaAzulCian
    )
)

val GradienteImpulsaDiagonal = Brush.linearGradient(
    colors = listOf(
        ImpulsaMagenta,
        ImpulsaMorado,
        ImpulsaAzulCian
    )
)
