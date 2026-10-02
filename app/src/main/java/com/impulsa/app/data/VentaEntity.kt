package com.impulsa.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa una venta registrada por el asesor.
 * Es la única tabla que la app necesita: todo lo que se muestra en
 * Dashboard, Historial, Detalle y Desempeño se calcula a partir de esta lista.
 */
@Entity(tableName = "ventas")
data class VentaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cliente: String,
    val concepto: String,
    val valor: Double,
    val formaPago: String,
    val fecha: Long,
    val nota: String? = null,
    // true cuando ya se confirmó la subida a Firebase (solo informativo en la UI)
    val sincronizada: Boolean = false
)
