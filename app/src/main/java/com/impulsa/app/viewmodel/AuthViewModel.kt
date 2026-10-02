package com.impulsa.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.impulsa.app.data.Asesor
import com.impulsa.app.data.AsesorDemo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * Autenticación con Firebase Auth y manejo de sesión.
 */
class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val _asesorActual = MutableStateFlow<Asesor?>(null)
    val asesorActual: StateFlow<Asesor?> = _asesorActual.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    init {
        val usuarioActual = auth.currentUser
        if (usuarioActual != null) {
            _asesorActual.value = crearAsesorDesdeUser(usuarioActual)
        }
    }

    fun iniciarSesion(correo: String, clave: String, alTerminar: (Boolean) -> Unit) {
        val correoTrim = correo.trim()
        if (correoTrim.isBlank() || clave.isBlank() || clave.length < 6) {
            _error.value = "Correo o contraseña incorrectos"
            alTerminar(false)
            return
        }

        viewModelScope.launch {
            _cargando.value = true
            _error.value = null

            try {
                // Intentar iniciar sesión en Firebase Auth
                val resultado = try {
                    auth.signInWithEmailAndPassword(correoTrim, clave).await()
                } catch (e: FirebaseAuthInvalidUserException) {
                    // El usuario no existe en Firebase Auth, intentamos crearlo
                    Log.d("AuthViewModel", "Usuario no encontrado en Firebase Auth (${e.message}), creando cuenta nueva...")
                    auth.createUserWithEmailAndPassword(correoTrim, clave).await()
                } catch (e: Exception) {
                    // Si falla el login por credenciales no encontradas en algunos proveedores
                    if ((e.message?.contains("no user record", ignoreCase = true) == true) ||
                        (e.message?.contains("USER_NOT_FOUND", ignoreCase = true) == true)) {
                        auth.createUserWithEmailAndPassword(correoTrim, clave).await()
                    } else {
                        throw e
                    }
                }

                val user = resultado.user
                if (user != null) {
                    _asesorActual.value = crearAsesorDesdeUser(user)
                    _error.value = null
                    _cargando.value = false
                    alTerminar(true)
                } else {
                    _error.value = "Correo o contraseña incorrectos"
                    _cargando.value = false
                    alTerminar(false)
                }
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                Log.e("AuthViewModel", "Credenciales inválidas en Firebase Auth", e)
                if (correoTrim.equals(AsesorDemo.CORREO, ignoreCase = true) && clave == AsesorDemo.CLAVE) {
                    _asesorActual.value = AsesorDemo.PERFIL
                    _error.value = null
                    _cargando.value = false
                    alTerminar(true)
                } else {
                    _error.value = "Correo o contraseña incorrectos"
                    _cargando.value = false
                    alTerminar(false)
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error durante la autenticación con Firebase", e)
                if (correoTrim.equals(AsesorDemo.CORREO, ignoreCase = true) && clave == AsesorDemo.CLAVE) {
                    _asesorActual.value = AsesorDemo.PERFIL
                    _error.value = null
                    _cargando.value = false
                    alTerminar(true)
                } else {
                    _error.value = "Correo o contraseña incorrectos"
                    _cargando.value = false
                    alTerminar(false)
                }
            }
        }
    }

    fun cerrarSesion() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Error al cerrar sesión en Firebase", e)
        }
        _asesorActual.value = null
    }

    private fun crearAsesorDesdeUser(user: FirebaseUser): Asesor {
        val email = user.email ?: AsesorDemo.CORREO
        if (email.equals(AsesorDemo.CORREO, ignoreCase = true)) {
            return AsesorDemo.PERFIL
        }
        val nombre = user.displayName?.ifBlank { null }
            ?: email.substringBefore("@").replaceFirstChar { it.uppercase() }
        val iniciales = nombre.split(" ")
            .mapNotNull { it.firstOrNull()?.uppercase() }
            .take(2)
            .joinToString("")
            .ifEmpty { "AS" }

        return Asesor(
            nombre = nombre,
            correo = email,
            telefono = "+57 311 355 6725",
            iniciales = iniciales,
            metaMensual = 20_000_000.0
        )
    }
}
