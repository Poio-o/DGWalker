# Changelog

## v0.2.7 - Eventos de Interacción con Corrutinas [SUB-06-3]

### Añadido
- Consulta SQL transaccional en `DigimonDao` (`setWalkingDigimon`) para alternar el Digimon activo de paseo (`isWalking`)
- Método `setWalkingDigimon` en `DigimonRepository` y `HomeViewModel` ejecutado con corrutinas de Kotlin (`viewModelScope.launch`)
- Sincronización automática remota en `HomeViewModel` (`init { syncFromRemote() }`) para recibir y reflejar el equipo asignado desde la plataforma web
- Conexión completa del Switch de activación en `DigimonDetailScreen` para establecer la criatura activa en la pantalla principal (`HomeScreen`)
- Cadenas internacionalizadas en inglés y español para las acciones de activación y estado

### Archivos modificados
- `app/src/main/java/com/alanturing/dgwalker/features/data/local/db/dao/DigimonDao.kt` - Añadida consulta `setWalkingDigimon`
- `app/src/main/java/com/alanturing/dgwalker/features/domain/repository/DigimonRepository.kt` - Añadido método en la interfaz
- `app/src/main/java/com/alanturing/dgwalker/features/data/repository/DigimonRepositoryImpl.kt` - Implementación del método en repositorio
- `app/src/main/java/com/alanturing/dgwalker/features/home/ui/HomeViewModel.kt` - Añadida sincronización inicial y función `setWalkingDigimon`
- `app/src/main/java/com/alanturing/dgwalker/features/digimon/ui/DigimonDetailScreen.kt` - Conexión de Switch de activación y actualización de textos
- `app/src/main/res/values/strings.xml` y `res/values-es/strings.xml` - Nuevos recursos de texto bilingües
- `app/src/test/java/com/alanturing/dgwalker/features/home/ui/HomeViewModelTest.kt` - Actualización del FakeRepository con soporte para tests de corrutinas
- `CHANGELOG.md` - Registro de cambios del proyecto Android

---

## v0.2.6 - Componentes Material Design 3, Pre-carga de Datos y Pantallas Reactivas [SUB-06-2]

### Añadido
- Integración de la librería **Coil** (`io.coil-kt:coil-compose:2.7.0`) para la carga asíncrona de imágenes y sprites desde URLs remotas mediante `AsyncImage`
- Sembrado inicial de datos (*Database Seeding*) en `DatabaseModule` mediante `RoomDatabase.Callback()`, poblando Room en la primera creación con 4 Digimons iniciales (*Tsunemon*, *Gekkomon*, *Betamon*, *BeelStarmon*) y objetos de comida (*Manzana Digimon*, *Carne Digimon*)
- Componente de UI `StatusIndicator` con `LinearProgressIndicator` de Material Design 3 y esquinas redondeadas para barras de hambre y amistad
- Componente modal `FeedBottomSheet` (`ModalBottomSheet`) para listar y seleccionar ítems del inventario al alimentar a una criatura
- Rediseño de `HomeScreen` siguiendo la interfaz deseada: Digimon activo en la plataforma circular azul, `StatusIndicator`, tarjeta DigiSteps y navegación con `BottomNavBar`
- Rediseño de `DigimonScreen` (Grid 2x2 de la colección de criaturas) con tarjetas estilizadas de bordes punteados
- Implementación de `DigimonDetailScreen` con carrusel de navegación (`arrow_back_ios_icon`, `arrow_forward_ios_icon`), edición de apodo, switch de activación y botón de alimentar

### Archivos creados
- `app/src/main/java/com/alanturing/dgwalker/core/ui/components/StatusIndicator.kt` - Componente de indicador de estado
- `app/src/main/java/com/alanturing/dgwalker/features/digimon/ui/components/FeedBottomSheet.kt` - Hoja modal para alimentar criaturas

### Archivos modificados
- `app/build.gradle.kts` - Añadida dependencia de Coil Compose (`coil-compose:2.7.0`)
- `app/src/main/java/com/alanturing/dgwalker/features/di/DatabaseModule.kt` - Añadido callback de sembrado inicial de Room
- `app/src/main/java/com/alanturing/dgwalker/features/home/ui/HomeScreen.kt` - Rediseño con Digimon activo, plataforma azul y DigiSteps
- `app/src/main/java/com/alanturing/dgwalker/features/digimon/ui/DigimonScreen.kt` - Rediseño del grid 2x2 de la colección
- `app/src/main/java/com/alanturing/dgwalker/features/digimon/ui/DigimonDetailScreen.kt` - Pantalla de detalle con carrusel, renombrado y botón de alimentar
- `app/src/main/java/com/alanturing/dgwalker/core/navigation/AppNavHost.kt` - Configuración de rutas y argumentos para el detalle de Digimon
- `CHANGELOG.md` - Registro de cambios del proyecto Android

---

## v0.2.5 - HomeViewModel y Estado Reactivo del Equipo [SUB-06-1]

### Añadido
- `HomeUiState` para encapsular reactivamente el estado de la pantalla principal (equipo de paseo, inventario disponible, mensajes e indicadores de carga y error)
- `HomeViewModel` anotado con `@HiltViewModel`, inyectando `DigimonRepository` e `InventoryRepository`
- Exposición de `StateFlow<HomeUiState>` reactivo combinando las fuentes de datos de Room (`getWalkingTeam()` y `getAllAvailableItems()`)
- Métodos de negocio en `HomeViewModel`: `feedDigimon` (actualización atómica de hambre y amistad con consumo de ítem), `updateNickname` (edición de apodo), `deleteDigimon` (baja de criaturas del equipo) y `syncFromRemote`
- Pruebas unitarias completas en `HomeViewModelTest.kt` utilizando `runTest`, `StandardTestDispatcher` y repositorios mock/fake (5/5 tests pasados)

### Archivos creados
- `app/src/test/java/com/alanturing/dgwalker/features/home/ui/HomeViewModelTest.kt` - Suite de pruebas unitarias para `HomeViewModel`

### Archivos modificados
- `app/src/main/java/com/alanturing/dgwalker/features/home/ui/HomeViewModel.kt` - Implementación de `HomeViewModel` y `HomeUiState`
- `app/build.gradle.kts` - Inclusión de dependencia `kotlinx-coroutines-test` para pruebas unitarias
- `CHANGELOG.md` - Registro de cambios del proyecto Android

---

## v0.2.4 - Autenticación Remota (Retrofit + JWT + Hilt + Compose) [SUB-02-2&3]

### Añadido
- DTOs de autenticación (`LoginRequest`, `RegisterRequest`, `UserDto`, `AuthResponseDto`, `ProfileResponseDto`, `ErrorResponseDto`) para comunicación con el backend REST Express
- `TokenManager` con `DataStore<Preferences>` para la persistencia local y recuperación reactiva del token JWT Bearer y datos del usuario
- Interceptor `AuthInterceptor` implementando `okhttp3.Interceptor` para inyectar automáticamente la cabecera `Authorization: Bearer <token>` en peticiones HTTP protegidas
- Servicio `AuthApiService` en Retrofit con los endpoints de registro (`POST /auth/register`), login (`POST /auth/login`) y perfil (`GET /auth/me`)
- Módulo `NetworkModule` en Hilt para proveer dependencias singleton de `Gson`, `HttpLoggingInterceptor`, `AuthInterceptor`, `OkHttpClient` con timeouts de 15s, `Retrofit` (`http://localhost:3000/api/v1/`) y `AuthApiService`
- Repositorio `AuthRepositoryImpl` implementando la interfaz `AuthService`, con manejo de persistencia automática de credenciales tras autenticación exitosa y extracción de mensajes de error devueltos por la API REST
- Método `getProfile` en `AuthService` y `AuthRepositoryImpl` para recuperar la información del perfil del usuario en la ruta protegida
- Validaciones en `RegisterViewModel` (longitud mínima de usuario >= 3, correo válido, contraseña >= 6 y coincidencia) y `LoginViewModel` utilizando recursos de texto internacionalizados (`strings.xml` en inglés y español)
- Indicador de carga `CircularProgressIndicator` en `LoginScreen` y `RegisterScreen` mientras `isLoading == true` deshabilitando los botones de acción

### Configuración de Red
- Permiso `android.permission.INTERNET` en `AndroidManifest.xml`
- Configuración `android:usesCleartextTraffic="true"` en `AndroidManifest.xml` para permitir peticiones HTTP locales durante el desarrollo
- Redirección de puerto local mediante comando ADB (`adb reverse tcp:3000 tcp:3000`) para pruebas temporales en dispositivo físico conectado por USB, mapeando `http://localhost:3000/api/v1/` directamente al backend de desarrollo local

### Archivos creados
- `app/src/main/java/com/alanturing/dgwalker/features/data/remote/dto/AuthDto.kt` - Definición de DTOs serializables con Gson para autenticación y respuestas de error
- `app/src/main/java/com/alanturing/dgwalker/features/data/local/TokenManager.kt` - Gestión y persistencia del token JWT y perfil en DataStore Preferences
- `app/src/main/java/com/alanturing/dgwalker/features/data/remote/AuthInterceptor.kt` - Interceptor OkHttp para la adición de cabeceras Bearer JWT
- `app/src/main/java/com/alanturing/dgwalker/features/data/remote/AuthApiService.kt` - Interfaz de endpoints Retrofit para autenticación
- `app/src/main/java/com/alanturing/dgwalker/features/di/NetworkModule.kt` - Módulo de inyección Hilt para OkHttp, Retrofit y servicios de red
- `app/src/main/java/com/alanturing/dgwalker/features/data/repository/AuthRepositoryImpl.kt` - Implementación del repositorio de autenticación remota
- `CHANGELOG.md` - Registro de cambios del proyecto Android

### Archivos modificados
- `app/src/main/AndroidManifest.xml` - Inclusión de permisos de red y tráfico HTTP claro
- `gradle/libs.versions.toml` - Declaración de librerías para Retrofit, Gson, OkHttp Logging Interceptor y DataStore Preferences
- `app/build.gradle.kts` - Inclusión de dependencias de Retrofit, OkHttp y DataStore
- `app/src/main/java/com/alanturing/dgwalker/features/data/model/User.kt` - Adaptación del modelo de dominio `User`
- `app/src/main/java/com/alanturing/dgwalker/features/domain/repository/AuthService.kt` - Definición del contrato con soporte para `getProfile`
- `app/src/main/java/com/alanturing/dgwalker/features/di/AuthModel.kt` - Actualización de inyección para usar `AuthRepositoryImpl`
- `app/src/main/java/com/alanturing/dgwalker/features/auth/ui/LoginViewModel.kt` - Integración con el flujo remoto de login y propagación de errores
- `app/src/main/java/com/alanturing/dgwalker/features/auth/ui/RegisterViewModel.kt` - Integración con el flujo remoto de registro, validaciones de campo y propagación de errores
- `app/src/main/java/com/alanturing/dgwalker/features/auth/ui/LoginScreen.kt` - Renderizado reactivo con `collectAsStateWithLifecycle()` e indicador de carga
- `app/src/main/java/com/alanturing/dgwalker/features/auth/ui/RegisterScreen.kt` - Renderizado reactivo with `collectAsStateWithLifecycle()` e indicador de carga
- `app/src/main/res/values/strings.xml` - Recursos de cadenas para mensajes de validación
- `app/src/main/res/values-es/strings.xml` - Traducción al español de las cadenas de validación

### Dependencias añadidas
```toml
retrofit = "2.11.0"
retrofit-converter-gson = "2.11.0"
okhttp-logging = "4.12.0"
androidx-datastore-preferences = "1.1.1"
```
