# Impulsa — Microproyecto Android (Taller 2)

App para que un asesor de ventas registre sus ventas, consulte su historial y siga su progreso hacia la meta mensual de comisión. Construida con Kotlin + Jetpack Compose, siguiendo el diseño del prototipo de Figma y los requisitos del Taller 2 (Electiva Desarrollo de Aplicaciones Móviles, Universidad del Cauca).

Integrantes: Maria José Peña Anacona, Daniel Esteban Arciniegas Barrera, Dayerli Katerine Tamayo Solarte.

---

## 0. Antes de abrir el proyecto: configurar Firebase (obligatorio para compilar)

El proyecto usa **Firebase Firestore** como servicio en línea. Como las credenciales son propias de cada proyecto de Firebase, no se pueden generar automáticamente; hay que crearlas una vez, así:

1. Entra a https://console.firebase.google.com y crea un proyecto nuevo (ej. "Impulsa-Taller2").
2. Dentro del proyecto, ve a **Compilación → Firestore Database → Crear base de datos** y actívala en **modo de prueba** (suficiente para el microproyecto).
3. Ve a **⚙️ Configuración del proyecto → Tus apps → Android** y registra la app con el `applicationId` **`com.impulsa.app`** (debe coincidir exactamente).
4. Descarga el archivo **`google-services.json`** que te ofrece Firebase.
5. Copia ese archivo dentro de la carpeta **`app/`** del proyecto (al mismo nivel que `app/build.gradle.kts`).
6. Abre el proyecto en Android Studio y deja que Gradle sincronice. Sin este archivo, el proyecto no compila (el plugin de Firebase lo exige).

Recomendación: al ser un proyecto académico sin datos sensibles, este archivo sí se puede subir al repositorio público para que el docente pueda clonar y compilar directamente.

---

## 1. Pantallas implementadas y requisito que cumplen

| Pantalla | Qué hace | Requisito de la guía |
|---|---|---|
| Login | Autenticación del asesor (credenciales demo) | Punto de entrada de la navegación |
| Dashboard | Resumen: nº de ventas, comisión, progreso de meta, últimas ventas | Pantalla obligatoria #1 |
| Registrar Venta | Formulario que guarda una venta en Room y la sincroniza con Firebase | Pantalla obligatoria #2 + Room (escritura) + servicio en línea |
| Historial de Ventas | Lista de ventas leídas desde Room, con buscador | Pantalla obligatoria #3 + Room (lectura) |
| Detalle de Venta | Detalle de una venta seleccionada del historial | Pantalla obligatoria #4 + navegación con argumentos |
| Desempeño | Total vendido, comisión acumulada y progreso hacia la meta | Pantalla obligatoria #5 |
| Perfil | Datos del asesor y cerrar sesión | Complementaria (parte del flujo de Figma) |
| Descripción y Créditos | Descripción de la app y nombres de los 3 integrantes | Exigida explícitamente por la guía |

Son **8 pantallas**, todas conectadas con Navigation Component (cumple el mínimo de 5 + la de créditos).

## 2. Cómo se implementó Room

- **Entity**: `VentaEntity` (`data/VentaEntity.kt`) — única tabla, guarda `cliente, concepto, valor, formaPago, fecha, nota, sincronizada`.
- **DAO**: `VentaDao` (`data/VentaDao.kt`) — `insertar()`, `obtenerTodas(): Flow<List<VentaEntity>>`, `obtenerPorId()`.
- **Database**: `AppDatabase` (`data/AppDatabase.kt`) — singleton creado en `ImpulsaApplication`.
- **Repository**: `VentaRepository` (`data/VentaRepository.kt`) — combina Room + Firebase para que el ViewModel no dependa directamente de ninguno de los dos.
- Se usa desde: Registrar Venta (escritura), Historial/Dashboard/Desempeño (lectura reactiva vía `Flow`), Detalle de Venta (lectura puntual).

## 3. Cómo se implementó ViewModel

- `AuthViewModel`: estado de sesión (`asesorActual`, login/logout).
- `VentaViewModel`: expone `ventas`, `totalVendido`, `comisionAcumulada` como `StateFlow`, y calcula el progreso de la meta. Toda la lógica de negocio vive aquí, no en los Composable.
- `ImpulsaViewModelFactory`: factory manual (sin librerías externas) para inyectar el repositorio en `VentaViewModel`.

## 4. Cómo se implementó Navigation Component

- `navigation/ImpulsaDestinations.kt`: define todas las rutas como constantes, incluida una ruta con argumento (`detalle_venta/{ventaId}`).
- `navigation/ImpulsaNavGraph.kt`: un único `NavHost` con un `composable()` por pantalla; la barra inferior (Dashboard/Historial/Desempeño/Perfil) se muestra u oculta según la ruta activa.

## 5. Cómo se implementó el servicio en línea (Firebase)

- **Qué información usa**: los mismos campos de la venta que se guardan en Room.
- **Qué operación realiza**: una escritura (`add`) en la colección `ventas` de Firestore.
- **Desde qué pantalla**: "Registrar Venta", justo después de guardar en Room (`VentaRepository.registrarVenta`, que llama a `FirebaseVentaService.subirVenta`).
- **Cómo se demuestra**: en el Detalle de Venta se ve la insignia "Sincronizada" cuando la subida fue exitosa, y el documento aparece en la consola de Firebase (Firestore → colección `ventas`).

## 6. Cómo se configuró el nombre e ícono

- Nombre: `app_name = "Impulsa"` (`res/values/strings.xml`), usado también como `android:label` en el `AndroidManifest.xml`.
- Ícono: adaptive icon (`res/mipmap-anydpi-v26/ic_launcher.xml`) con fondo del morado de marca y el motivo de barras ascendentes del logo de Impulsa; también se incluyó el logo original (`logo_impulsa.jpg`) en `res/drawable-*` para usarlo dentro de la app si se desea.

## 7. Criterios de diseño aplicados (mínimo 3, exigidos por la guía)

1. **Consistencia visual**: una sola paleta de color y un solo set de componentes (`TarjetaImpulsa`, `InsigniaImpulsa`, `AvatarIniciales`, `BarraProgreso`) reutilizados en las 8 pantallas, tomados directamente de los design tokens del Figma.
2. **Jerarquía visual**: en cada pantalla el dato más importante (comisión, valor de la venta, progreso de meta) se muestra con mayor tamaño y peso tipográfico que la información secundaria.
3. **Retroalimentación (feedback) al usuario**: barra de progreso animada hacia la meta, insignia de "Sincronizada" tras guardar una venta, mensajes de error visibles en formularios y diálogo de confirmación antes de cerrar sesión.
4. *(opcional, también presente)* **Navegación predecible**: barra inferior fija en las pantallas principales y flujo de "atrás" consistente en las pantallas secundarias (Registrar, Detalle, Créditos).

## 8. Capturas de pantalla que debes tomar para el documento de evidencias

1. Login.
2. Dashboard con al menos una venta registrada.
3. Registrar Venta (formulario lleno, antes de guardar).
4. Historial de ventas con 2-3 ventas.
5. Detalle de una venta mostrando la insignia "Sincronizada".
6. Desempeño con la barra de progreso avanzada.
7. Perfil.
8. Descripción y Créditos.
9. Ícono y nombre "Impulsa" en el launcher del emulador/teléfono.
10. Consola de Firebase → Firestore → colección `ventas` con al menos un documento (evidencia de la conexión en línea).

## 9. Partes del código para mostrar en la sustentación oral

- `VentaViewModel.kt`: cómo se calcula la comisión y el progreso de la meta sin lógica en la UI.
- `VentaRepository.kt`: cómo se coordina Room + Firebase en una sola operación.
- `ImpulsaNavGraph.kt`: cómo se define la ruta con argumento (`detalle_venta/{ventaId}`) y cómo se recupera el `ventaId`.
- `VentaDao.kt`: el `Flow<List<VentaEntity>>` que hace que la UI se actualice sola cuando cambian los datos.
- `FirebaseVentaService.kt`: la llamada a Firestore y cómo se convierte en `suspend fun` con `suspendCancellableCoroutine`.

---

## 10. Git: estructura y estrategia de colaboración

**Qué subir al repositorio**: todo el contenido de esta carpeta (incluye `app/google-services.json` una vez lo generes — ver sección 0), **excepto** lo que ya excluye el `.gitignore` incluido (`/build`, `/.gradle`, `/.idea`, `local.properties`).

**Ramas sugeridas** (cada integrante trabaja en la suya y luego se integra a `main` por Pull Request):

- `main` — versión estable, siempre debe compilar.
- `feature/maria-jose` — ej. pantallas Login, Dashboard y Perfil.
- `feature/daniel` — ej. Registrar Venta, Historial y Detalle de Venta.
- `feature/dayerli` — ej. Desempeño, Créditos, integración con Firebase y documentación.

**Cómo evidenciar colaboración real**:
1. Cada integrante hace `commits` pequeños y frecuentes en su propia rama (no un solo commit gigante al final).
2. Los mensajes de commit deben describir qué se hizo, ej. `feat: agrega pantalla de historial de ventas`, `fix: valida valor numérico en registrar venta`.
3. Al terminar una parte, se abre un Pull Request hacia `main` y otro integrante lo revisa antes de aprobar el merge (así queda visible la revisión cruzada, no solo el código).
4. Evitar hacer commits "a nombre de otro" o copiar y pegar el mismo cambio en varias ramas solo para simular participación: el docente revisa el contenido real de cada commit.

**Comandos base**:
```bash
git init
git remote add origin <url-del-repo-publico>
git add .
git commit -m "chore: estructura inicial del proyecto Impulsa"
git push -u origin main

# cada integrante, en su propia rama:
git checkout -b feature/tu-nombre
git push -u origin feature/tu-nombre
```
