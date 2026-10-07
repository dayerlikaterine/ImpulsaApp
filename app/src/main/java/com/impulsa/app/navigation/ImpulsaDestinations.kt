// Daniel
package com.impulsa.app.navigation

/** Rutas de Navigation Component. Todas las pantallas de la app quedan listadas aquí. */

object ImpulsaDestinations {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val REGISTRAR_VENTA = "registrar_venta"
    const val HISTORIAL = "historial"
    const val DETALLE_VENTA = "detalle_venta/{ventaId}"
    const val DESEMPENO = "desempeno"
    const val PERFIL = "perfil"
    const val CREDITOS = "creditos"

    fun detalleVenta(ventaId: Long) = "detalle_venta/$ventaId"

    /** Pantallas principales, con barra de navegación inferior visible. */
    val conBarraInferior = setOf(DASHBOARD, HISTORIAL, DESEMPENO, PERFIL)
}
