# R-MortyHub

## Arquitectura del Proyecto

El proyecto sigue la arquitectura **MVVM** con las siguientes capas principales:

- **Presentación (View)**: Utiliza Jetpack Compose para construir la interfaz de usuario de manera declarativa. Incluye composables reutilizables como `ButtonComponent`, `LoadingComponent`, y pantallas como `HomeScreen` y `SplashScreen`.
- **ViewModel**: Maneja el estado de la UI y la lógica de negocio. Por ejemplo, `HomeViewModel` gestiona la carga de personajes y el manejo de errores.
- **Repositorio**: Abstrae la capa de datos, proporcionando una interfaz unificada para acceder a los datos. `Repository` implementa `IRepository` y utiliza `CharacterService` para llamadas a la API.
- **API**: Utiliza Retrofit para realizar llamadas HTTP a la API de Rick and Morty. Incluye manejo de respuestas con clases selladas `DataResult` para success y error.
- **Inyección de Dependencias**: Hilt se utiliza para inyectar dependencias, facilitando la gestión de instancias y pruebas.
- **Navegación**: Jetpack Navigation para el control de pantallas y navegación entre ellas.


## 🎯 Decisiones técnicas tomadas
- [x] 📚 **Incorporar Hilt** Código más limpio, fácil de mantener. Las dependencias se gestionan de forma centralizada.
- [x] 📚 **Incorporar navigation** Flujo de navegación claro, seguro y fácil de seguir.
- [x] 📚 **Incorporar repository ** Desacopla la lógica de la fuente de datos.
- [x] 📚 **Implementar clases generica y sellada en peticiones** (DataResult) PTratamiento explícito y seguro de los casos éxito/error. (apiResponse) Reutilización de lógica para todas las peticiones de red y menos propenso a errores.

## 🎯 Qué quedó fuera por falta de tiempo
- [x] 🔧 **Patanlla de detalle del personajes** Ver información extendida de cada personaje
- [x] 🔧 **Busqueda por personajes** Filtrar personajes por nombre directamente desde la lista principal
- [x] 🔧 **Pantalla de personajes favorios**
- [x] 🔧 **Repositorio local**

## 🎯 Qué mejorarías con más tiempo
- [x] 🎨 **Rediseñar** SplashScreen y la HomeScreen para una experiencia visual más pulida y moderna.

## 🎯 Si utilizaste AI, en qué partes y cómo
- [x] ⚡️ **Traducción**  Asistió en la generación de archivos de strings (strings.xml) en inglés y español
- [x] ⚡️ **Componente de Carga (Shimmer)** Ayudó a generar el código base del efecto shimmer para el placeholder de las imágenes de los personajes