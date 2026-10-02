package com.impulsa.app.data

/**
 * Datos del asesor que inicia sesión. No se guarda en Room porque el
 * microproyecto solo maneja una cuenta de demostración; lo que sí se
 * persiste localmente y en línea son las ventas que ese asesor registra.
 */
data class Asesor(
    val nombre: String,
    val correo: String,
    val telefono: String,
    val iniciales: String,
    val metaMensual: Double
)

/** Credenciales de demostración usadas por el login. */
object AsesorDemo {
    const val CORREO = "daniel@impulsa.co"
    const val CLAVE = "impulsa123"

    val PERFIL = Asesor(
        nombre = "Daniel Arciniegas",
        correo = CORREO,
        telefono = "+57 311 355 6725",
        iniciales = "DA",
        metaMensual = 20_000_000.0
    )
}
