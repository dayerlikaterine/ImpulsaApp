package com.impulsa.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.impulsa.app.navigation.ImpulsaApp
import com.impulsa.app.ui.theme.ImpulsaTheme
import com.impulsa.app.viewmodel.AuthViewModel
import com.impulsa.app.viewmodel.ImpulsaViewModelFactory
import com.impulsa.app.viewmodel.VentaViewModel

class MainActivity : ComponentActivity() {

    // AuthViewModel no necesita el repositorio, así que usa la factory por defecto.
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as ImpulsaApplication
        val factory = ImpulsaViewModelFactory(app.repositorio)

        setContent {
            ImpulsaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val ventaViewModel: VentaViewModel = viewModel(factory = factory)
                    ImpulsaApp(authViewModel = authViewModel, ventaViewModel = ventaViewModel)
                }
            }
        }
    }
}
