package com.impulsa.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {

    @Insert
    suspend fun insertar(venta: VentaEntity): Long

    @Query("SELECT * FROM ventas ORDER BY fecha DESC")
    fun obtenerTodas(): Flow<List<VentaEntity>>

    @Query("SELECT * FROM ventas WHERE id = :ventaId")
    suspend fun obtenerPorId(ventaId: Long): VentaEntity?

    @Query("UPDATE ventas SET sincronizada = 1 WHERE id = :ventaId")
    suspend fun marcarSincronizada(ventaId: Long)
}
