
# 📚 BookExplorer

BookExplorer es una aplicación Android desarrollada en Kotlin que permite a los usuarios buscar libros mediante la API de [OpenLibrary](https://openlibrary.org/developers/api). La app muestra los resultados en un RecyclerView y permite consultar información detallada de cada libro seleccionado.

---

## 🚀 Funcionalidades principales

- 🔍 **Búsqueda de libros** por título, autor o tema.
- 📚 **Visualización de resultados** en una lista elegante con imágenes, títulos y descripciones.
- 📄 **Pantalla de detalle** para cada libro con portada, autor, descripción y más.
- ⚙️ Arquitectura **MVVM** limpia y modular.
- ⚡ **Optimización de búsqueda** con debounce (evita llamadas excesivas a la API).
- 🔗 Uso de Retrofit para conectarse a la API de OpenLibrary.
- 📦 Transformación de datos mediante un `Mapper` dedicado.

---

## 🧱 Tecnologías utilizadas

- 🧠 **MVVM (Model-View-ViewModel)**
- 🔌 **Retrofit 2** - Cliente HTTP para Android
- 💨 **Kotlin Coroutines** - Asincronía simplificada
- 🧪 **LiveData** - Observabilidad de datos
- 🧼 **ViewBinding** - Manejo seguro y limpio de vistas
- 🖼️ **Glide** - Carga de imágenes eficiente
- 🌐 **OpenLibrary API** - Fuente de datos abiertos de libros

---

## 🧠 Arquitectura y flujo de la app

La app sigue el patrón MVVM, que permite una clara separación de responsabilidades:

MainActivity (View)
  └── observa libros y errores del ViewModel
  └── lanza búsqueda al ViewModel
      └── MainViewModel
            └── llama a BookRepository
                  └── RetrofitInstance.api.searchOpenLibraryBooks()
                      └── recibe JSON (OpenLibraryResponse)
                          └── OpenLibraryMapper → transforma a BookItem
            └── expone resultados como LiveData
  └── BookAdapter → renderiza libros en RecyclerView
  └── Clic en libro → DetailActivity (muestra datos)

---

## 📂 Estructura del proyecto

📁 com.pedrobaonza.bookexplorer
│
├── ui
│   └── main
│       ├── MainActivity.kt       # Pantalla principal con búsqueda
│       ├── DetailActivity.kt     # Pantalla de detalle del libro
│       └── BookAdapter.kt        # Adaptador del RecyclerView
│
├── data
│   ├── model
│   │   ├── BookItem.kt           # Modelo usado en la UI
│   │   ├── OpenLibraryBook.kt    # Modelo crudo de la API
│   │   ├── OpenLibraryResponse.kt
│   │   └── OpenLibraryMapper.kt  # Transforma modelo crudo → limpio
│   │
│   ├── api
│   │   ├── GoogleBooksApi.kt     # Interfaz de Retrofit
│   │   └── RetrofitInstance.kt   # Configuración singleton de Retrofit
│   │
│   └── repository
│       └── BookRepository.kt     # Orquestador de acceso a datos
│
└── viewmodel
    └── MainViewModel.kt          # Lógica de negocio y LiveData

---

## 📥 Cómo ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/bookexplorer.git
   cd bookexplorer
   ```

2. Ábrelo con Android Studio.

3. Asegúrate de tener una conexión a Internet para las llamadas a la API.

4. Ejecuta el proyecto en un emulador o dispositivo físico.

---

## 📌 Notas importantes

- La app usa la API pública de OpenLibrary, que no requiere autenticación.
- Se implementa una lógica de **debounce** para evitar sobrecargar el servidor con búsquedas consecutivas rápidas.
- Toda la lógica de red y transformación de datos está desacoplada de la UI, facilitando el mantenimiento y testing.

---

## 🧪 Mejoras futuras

- Añadir función de "Favoritos" con almacenamiento local (Room o SharedPreferences).
- Implementar historial de búsqueda.
- Soporte offline y caché.
- Tests unitarios para el ViewModel y Repository.
- Paginación de resultados con Jetpack Paging.

---

## 👨‍💻 Autor

**Pedro Baonza**  
Desarrollador Android | Kotlin & Jetpack | Apasionado por la arquitectura limpia  
[LinkedIn](https://www.linkedin.com/) | [GitHub](https://github.com/tu-usuario)

---

## 📖 Licencia

MIT License - libre para uso y modificación con atribución.
