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

```plaintext
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
