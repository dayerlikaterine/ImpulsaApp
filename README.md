# Impulsa — Microproyecto Android (Taller 2)

**Impulsa** es una aplicación móvil desarrollada para que un asesor de ventas pueda **registrar ventas, consultar su historial y revisar su avance hacia una meta mensual de comisión**.

Fue desarrollada con **Kotlin y Jetpack Compose**, siguiendo el prototipo de Figma y los requisitos del Taller de la asignatura **Desarrollo de Aplicaciones Móviles**.

**Integrantes:**
Maria José Peña Anacona, Daniel Esteban Arciniegas Barrera y Dayerli Katerine Tamayo Solarte.

---

## 1. Firebase

La aplicación utiliza **Firebase Firestore** como servicio en línea.

Para ejecutar el proyecto se debe:

1. Crear un proyecto en Firebase.
2. Activar Firestore en modo de prueba.
3. Registrar la aplicación con el identificador `com.impulsa.app`.
4. Descargar `google-services.json`.
5. Colocar el archivo dentro de la carpeta `app/`.
6. Abrir el proyecto en Android Studio y sincronizar Gradle.

---

## 2. Pantallas

La aplicación cuenta con 8 pantallas:

| Pantalla                   | Función                                     |
| -------------------------- | ------------------------------------------- |
| **Login**                  | Inicio de sesión del asesor.                |
| **Dashboard**              | Resumen de ventas, comisión y meta.         |
| **Registrar Venta**        | Registro de nuevas ventas.                  |
| **Historial**              | Consulta y búsqueda de ventas.              |
| **Detalle de Venta**       | Información completa de una venta.          |
| **Desempeño**              | Total vendido, comisión y progreso.         |
| **Perfil**                 | Datos del asesor y cierre de sesión.        |
| **Descripción y Créditos** | Información de la aplicación e integrantes. |

La navegación entre las pantallas se realizó con **Navigation Component**.

---

## 3. Room

Se utilizó **Room** para almacenar las ventas de forma local.

Los principales componentes son:

* `VentaEntity`: información de la venta.
* `VentaDao`: inserción y consulta de ventas.
* `AppDatabase`: configuración de la base de datos.
* `VentaRepository`: comunicación entre Room y Firebase.

---

## 4. ViewModel

Se utilizaron ViewModel para manejar la lógica de la aplicación.

* **AuthViewModel:** controla el inicio y cierre de sesión.
* **VentaViewModel:** obtiene las ventas y calcula el total vendido, la comisión y el progreso de la meta.

---

## 5. Firebase

Al registrar una venta:

**Registrar venta → Room → Firebase Firestore**

La información se guarda localmente y luego se sincroniza con Firebase en la colección `ventas`.

Cuando la operación es exitosa, el detalle de la venta muestra la etiqueta **"Sincronizada"**.

---

## 6. Diseño

Se aplicaron principalmente tres criterios:

* **Consistencia visual:** mismos colores y componentes en las pantallas.
* **Jerarquía visual:** los datos importantes tienen mayor tamaño y relevancia.
* **Retroalimentación:** mensajes, validaciones, barra de progreso y confirmaciones para las acciones del usuario.

---


La rama `main` debe mantenerse con una versión funcional del proyecto.
