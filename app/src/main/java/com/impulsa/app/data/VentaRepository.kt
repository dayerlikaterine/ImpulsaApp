package com.impulsa.app.data

import com.impulsa.app.remote.FirebaseVentaService
import kotlinx.coroutines.flow.Flow

/**
 * Punto único desde el que el ViewModel lee y escribe ventas.
 * Room es la fuente de verdad local; Firebase es solo un respaldo en línea.
 */
class VentaRepository(
    private val dao: VentaDao,
    private val servicioFirebase: FirebaseVentaService
) {
    val ventas: Flow<List<VentaEntity>> = dao.obtenerTodas()

    suspend fun obtenerPorId(id: Long): VentaEntity? = dao.obtenerPorId(id)

    /**
     * Guarda la venta en Room y luego intenta sincronizarla con Firestore.
     * Devuelve true si además quedó sincronizada en línea.
     */
    suspend fun registrarVenta(venta: VentaEntity, asesorCorreo: String? = null): Boolean {
        val idGenerado = dao.insertar(venta)
        val sincronizada = servicioFirebase.subirVenta(venta.copy(id = idGenerado), asesorCorreo)
        if (sincronizada) {
            dao.marcarSincronizada(idGenerado)
        }
        return sincronizada
    }
}
