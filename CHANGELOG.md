# Changelog

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
