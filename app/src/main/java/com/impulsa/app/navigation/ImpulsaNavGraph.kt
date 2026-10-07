//parte de daniel
package com.impulsa.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.impulsa.app.ui.components.BarraInferiorImpulsa
import com.impulsa.app.ui.components.ItemBarraInferior
import com.impulsa.app.ui.components.rutaTieneBarraInferior
import com.impulsa.app.ui.screens.CreditosScreen
import com.impulsa.app.ui.screens.DashboardScreen
import com.impulsa.app.ui.screens.DesempenoScreen
import com.impulsa.app.ui.screens.DetalleVentaScreen
import com.impulsa.app.ui.screens.HistorialVentasScreen
import com.impulsa.app.ui.screens.LoginScreen
import com.impulsa.app.ui.screens.PerfilScreen
import com.impulsa.app.ui.screens.RegistrarVentaScreen
import com.impulsa.app.viewmodel.AuthViewModel
import com.impulsa.app.viewmodel.VentaViewModel


private val ITEMS_BARRA_INFERIOR = listOf(
    ItemBarraInferior(ImpulsaDestinations.DASHBOARD, "Inicio", Icons.Filled.Home),
    ItemBarraInferior(ImpulsaDestinations.HISTORIAL, "Ventas", Icons.Filled.List),
    ItemBarraInferior(ImpulsaDestinations.DESEMPENO, "Desempeño", Icons.Filled.BarChart),
    ItemBarraInferior(ImpulsaDestinations.PERFIL, "Perfil", Icons.Filled.Person)
)

@Composable
fun ImpulsaApp(authViewModel: AuthViewModel, ventaViewModel: VentaViewModel) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val mostrarBarraInferior = rutaTieneBarraInferior(backStackEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            if (mostrarBarraInferior) {
                BarraInferiorImpulsa(navController, ITEMS_BARRA_INFERIOR)
            }
        }
    ) { paddingInterno ->
        NavHost(
            navController = navController,
            startDestination = ImpulsaDestinations.LOGIN,
            modifier = Modifier.padding(paddingInterno)
        ) {
            composable(ImpulsaDestinations.LOGIN) {
            
                LoginScreen(
                    authViewModel = authViewModel,
                    alIniciarSesion = {
                        navController.navigate(ImpulsaDestinations.DASHBOARD) {
                            popUpTo(ImpulsaDestinations.LOGIN) { inclusive = true }
                        }
                    }
                )
            }

            composable(ImpulsaDestinations.DASHBOARD) {
                val asesor by authViewModel.asesorActual.collectAsState()
                asesor?.let {
                    DashboardScreen(
                        asesor = it,
                        ventaViewModel = ventaViewModel,
                        alRegistrarVenta = { navController.navigate(ImpulsaDestinations.REGISTRAR_VENTA) }
                    )
                }
            }

            
            composable(ImpulsaDestinations.REGISTRAR_VENTA) {
                val asesor by authViewModel.asesorActual.collectAsState()
                RegistrarVentaScreen(
                    ventaViewModel = ventaViewModel,
                    asesor = asesor,
                    alVolver = { navController.popBackStack() },
            
                    alGuardar = { navController.popBackStack() }
                )
            }

            composable(ImpulsaDestinations.HISTORIAL) {
                HistorialVentasScreen(
                    ventaViewModel = ventaViewModel,
                    alSeleccionarVenta = { id ->
                        navController.navigate(ImpulsaDestinations.detalleVenta(id))
                    }
                )
            }

            
            composable(
                route = ImpulsaDestinations.DETALLE_VENTA,
                arguments = listOf(navArgument("ventaId") { type = NavType.LongType })
            ) { entrada ->
                val ventaId = entrada.arguments?.getLong("ventaId") ?: 0L
                DetalleVentaScreen(
                    ventaId = ventaId,
                    ventaViewModel = ventaViewModel,
                    alVolver = { navController.popBackStack() }
                )
            }

            
            composable(ImpulsaDestinations.DESEMPENO) {
                val asesor by authViewModel.asesorActual.collectAsState()
                asesor?.let { DesempenoScreen(asesor = it, ventaViewModel = ventaViewModel) }
            }

            
             composable(ImpulsaDestinations.PERFIL) {
                val asesor by authViewModel.asesorActual.collectAsState()
                  asesor?.let {
                     PerfilScreen(
                        asesor = it,
                        alVerCreditos = { navController.navigate(ImpulsaDestinations.CREDITOS) },
                        alCerrarSesion = {
                            authViewModel.cerrarSesion()
                            navController.navigate(ImpulsaDestinations.LOGIN) {
                                popUpTo(0)
                            }
                        }
                    )
                }
            }

             composable(ImpulsaDestinations.CREDITOS) {
                 CreditosScreen(alVolver = { navController.popBackStack() })
            }
        }

    }
}
