package com.impulsa.app.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.impulsa.app.data.AsesorDemo
import com.impulsa.app.data.VentaEntity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Servicio para subir las ventas a la colección "ventas" de Firebase Firestore.
 */
class FirebaseVentaService {

    private val db by lazy { FirebaseFirestore.getInstance() }

    suspend fun subirVenta(venta: VentaEntity, asesorCorreo: String? = null): Boolean = suspendCancellableCoroutine { continuacion ->
        try {
            val usuarioActual = FirebaseAuth.getInstance().currentUser
            val emailFinal = asesorCorreo?.ifBlank { null }
                ?: usuarioActual?.email?.ifBlank { null }
                ?: AsesorDemo.CORREO

            val uidFinal = usuarioActual?.uid ?: ""

            val documento = hashMapOf(
                "cliente" to venta.cliente,
                "concepto" to venta.concepto,
                "valor" to venta.valor,
                "formaPago" to venta.formaPago,
                "fecha" to venta.fecha,
                "nota" to (venta.nota ?: ""),
                "usuarioEmail" to emailFinal,
                "usuarioUid" to uidFinal
            )

            Log.d("FirebaseVentaService", "Subiendo venta a Firestore con correo: $emailFinal")

            db.collection("ventas").add(documento)
                .addOnSuccessListener { docRef ->
                    Log.d("FirebaseVentaService", "Venta guardada con éxito en Firestore. ID: ${docRef.id}")
                    if (continuacion.isActive) continuacion.resume(true)
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseVentaService", "Error al guardar venta en Firestore: ${exception.localizedMessage}", exception)
                    if (continuacion.isActive) continuacion.resume(false)
                }
        } catch (e: Exception) {
            Log.e("FirebaseVentaService", "Excepción al intentar comunicar con Firestore", e)
            if (continuacion.isActive) continuacion.resume(false)
        }
    }
}
