package com.impulsa.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.impulsa.app.data.VentaRepository

/**
 * Factory sencilla (sin Hilt/Dagger, para que sea fácil de explicar) que
 * le entrega al VentaViewModel el repositorio creado en ImpulsaApplication.
 */
class ImpulsaViewModelFactory(private val repositorio: VentaRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VentaViewModel::class.java)) {
            return VentaViewModel(repositorio) as T
        }
        throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
    }
}
