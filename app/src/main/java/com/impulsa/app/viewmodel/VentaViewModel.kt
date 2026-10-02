package com.impulsa.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.impulsa.app.data.VentaEntity
import com.impulsa.app.data.VentaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** Porcentaje de comisión aplicado sobre el total vendido (regla fija y simple del prototipo). */
private const val PORCENTAJE_COMISION = 0.08

enum class EstadoSincronizacion { INACTIVO, SINCRONIZANDO, EXITO, ERROR }

/**
 * Estado compartido por Dashboard, Registrar Venta, Historial, Detalle y Desempeño.
 * Toda la lógica de negocio (totales, comisión, progreso de meta) vive aquí,
 * nunca dentro de los Composable.
 */
class VentaViewModel(private val repositorio: VentaRepository) : ViewModel() {

    val ventas: StateFlow<List<VentaEntity>> = repositorio.ventas
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _estadoSincronizacion = MutableStateFlow(EstadoSincronizacion.INACTIVO)
    val estadoSincronizacion: StateFlow<EstadoSincronizacion> = _estadoSincronizacion.asStateFlow()

    val totalVendido: StateFlow<Double> = ventas
        .map { lista -> lista.sumOf { it.valor } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val comisionAcumulada: StateFlow<Double> = totalVendido
        .map { total -> total * PORCENTAJE_COMISION }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun registrarVenta(
        cliente: String,
        concepto: String,
        valor: Double,
        formaPago: String,
        nota: String?,
        asesorCorreo: String? = null,
        alTerminar: (exito: Boolean, sincronizada: Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _estadoSincronizacion.value = EstadoSincronizacion.SINCRONIZANDO
            val nuevaVenta = VentaEntity(
                cliente = cliente,
                concepto = concepto,
                valor = valor,
                formaPago = formaPago,
                fecha = System.currentTimeMillis(),
                nota = nota?.ifBlank { null }
            )
            val sincronizada = repositorio.registrarVenta(nuevaVenta, asesorCorreo)
            _estadoSincronizacion.value =
                if (sincronizada) EstadoSincronizacion.EXITO else EstadoSincronizacion.ERROR
            alTerminar(true, sincronizada)
        }
    }

    suspend fun obtenerPorId(id: Long): VentaEntity? = repositorio.obtenerPorId(id)

    fun progresoMeta(metaMensual: Double): Float {
        if (metaMensual <= 0.0) return 0f
        return (comisionAcumulada.value / metaMensual).toFloat().coerceIn(0f, 1f)
    }
}
