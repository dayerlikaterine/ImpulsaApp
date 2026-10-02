# Impulsa — Aplicación Móvil de Gestión de Ventas y Comisiones

**Impulsa** es una aplicación nativa para Android diseñada para ayudar a asesores comerciales a registrar sus ventas diarias, realizar un seguimiento de sus comisiones en Pesos Colombianos ($ COP) y evaluar su progreso hacia la meta mensual.

Desarrollada para la asignatura **Electiva: Desarrollo de Aplicaciones Móviles** de la **Universidad del Cauca**.

---

## 👥 Integrantes del Proyecto

- **Daniel Esteban Arciniegas Barrera**
- **Maria José Peña Anacona**
- **Dayerli Katerine Tamayo Solarte**

**Profesor:** Ph.D. Cristhian Figueroa  
**Institución:** Universidad del Cauca — Departamento de Telemática  

---

## 🚀 Características Principales

- **Arquitectura Offline-First:** Las ventas se guardan primero en la base de datos local SQLite mediante **Room**, permitiendo usar la app sin conexión a Internet.
- **Respaldo en la Nube:** Sincronización automática de ventas hacia la base de datos **Firebase Firestore**.
- **Autenticación de Usuarios:** Control de sesión con **Firebase Auth**.
- **Cálculo de Comisiones:** Cálculo automático del 8% de comisión sobre el valor acumulado en Pesos Colombianos.
- **Progreso de Meta:** Visualización gráfica porcentual del avance hacia la meta mensual de $20.000.000 COP.
- **Interfaz Moderna:** Diseñada con **Jetpack Compose** y el sistema de diseño **Material 3**.

---

## 📱 Pantallas de la Aplicación

1. **Inicio de Sesión (`LoginScreen`):** Autenticación de usuario con correo y contraseña.
2. **Tablero Principal (`DashboardScreen`):** Resumen de ventas, comisiones, avance de meta y accesos rápidos.
3. **Registrar Venta (`RegistrarVentaScreen`):** Formulario con validación para cliente, concepto, valor monetario, forma de pago y notas.
4. **Historial de Ventas (`HistorialVentasScreen`):** Lista de transacciones registradas localmente en el dispositivo.
5. **Detalle de Venta (`DetalleVentaScreen`):** Desglose e insignia de sincronización en línea.
6. **Desempeño (`DesempenoScreen`):** Estadísticas y gráfico de cumplimiento.
7. **Perfil (`PerfilScreen`):** Información del asesor y opción para cerrar sesión.
8. **Descripción y Créditos (`CreditosScreen`):** Información del equipo de desarrollo y resumen de la aplicación.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Base de Datos Local:** Room (SQLite)
- **Arquitectura:** MVVM (Model-View-ViewModel) con `StateFlow`
- **Navegación:** Jetpack Navigation Compose
- **Servicios en Nube:** Firebase Auth & Cloud Firestore
- **Control de Versiones:** Git & GitHub

---

## ⚙️ Configuración e Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/dayerlikaterine/ImpulsaApp.git
   ```
2. Abrir el proyecto en **Android Studio**.
3. Sincronizar el proyecto con Gradle (**Sync Project with Gradle Files**).
4. Compilar e instalar la aplicación en un dispositivo físico o emulador con Android 7.0 (API 24) o superior.

---

## 🌿 Estructura de Ramas en Git

- `main` / `master`: Versión principal de producción.
- `feature/ui-compose-theme`: Implementación del sistema de diseño, colores y componentes visuales en Compose.
- `feature/room-database`: Configuración de base de datos local SQLite, entidades y DAOs.
- `feature/firebase-integration`: Integración de Firebase Auth y servicios de sincronización en Firestore.
