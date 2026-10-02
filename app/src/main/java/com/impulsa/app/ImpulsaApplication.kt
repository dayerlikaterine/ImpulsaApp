package com.impulsa.app

import android.app.Application
import com.impulsa.app.data.AppDatabase
import com.impulsa.app.data.VentaRepository
import com.impulsa.app.remote.FirebaseVentaService

class ImpulsaApplication : Application() {

    // Se crean una sola vez y se reutilizan en toda la app (patrón simple, sin librerías de inyección).
    val repositorio: VentaRepository by lazy {
        val dao = AppDatabase.obtenerInstancia(this).ventaDao()
        VentaRepository(dao, FirebaseVentaService())
    }
}
