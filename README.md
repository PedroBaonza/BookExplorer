# 📚 Book Explorer

![Banner](https://i.imgur.com/jY7eX2R.png)

## 📖 Descripción

**Book Explorer** es una aplicación Android desarrollada en **Kotlin** que permite explorar y descubrir libros a través de la API pública de [Google Books](https://developers.google.com/books). La aplicación está construida siguiendo la arquitectura **MVVM** y utiliza tecnologías modernas como Retrofit, Kotlin Coroutines, LiveData y Glide.

---

## 🚀 Funcionalidades

- 🔍 **Búsqueda Rápida:** Encuentra libros fácilmente por título, autor o cualquier palabra clave.
- 📋 **Listado Detallado:** Muestra una lista clara con portadas, títulos, autores y una breve descripción de cada libro.
- 📖 **Detalles del Libro:** Información ampliada, incluyendo portada grande, autores y descripción completa.
- 🌐 **Consumo de APIs REST:** Utilizando Retrofit 2 con Gson para peticiones eficientes y estructuradas.

---

## 🎨 Capturas de pantalla

*(Aquí puedes agregar imágenes de tu app funcionando)*

| Pantalla Principal | Pantalla de Detalle |
|--------------------|---------------------|
| ![Home](https://via.placeholder.com/250x500.png?text=Lista+de+Libros) | ![Detail](https://via.placeholder.com/250x500.png?text=Detalle+Libro) |

---

## 🛠 Tecnologías

- **Kotlin**
- **Retrofit 2** + **Gson Converter**
- **MVVM Architecture**
- **Kotlin Coroutines**
- **LiveData & ViewModel**
- **RecyclerView**
- **Glide** (Carga de imágenes)
- **Material Design Components**

---

## 📦 Estructura del proyecto

```
📁 data
│   ├── api             ← Retrofit interfaces
│   ├── model           ← Modelos de datos
│   └── repository      ← Repositorios
📁 ui
│   └── main            ← Actividades, ViewModels y Adapters
│
📁 utils                ← Utilidades generales
```

---

## ▶️ Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/tu_usuario/book-explorer.git
```

2. Ábrelo en Android Studio.

3. Construye y ejecuta en tu dispositivo físico o emulador.

---

## 🌟 Autor

👤 **Tu Nombre**

- GitHub: [@tu_usuario](https://github.com/tu_usuario)
- LinkedIn: [Tu LinkedIn](https://linkedin.com/in/tu_perfil)

---

## 📄 Licencia

Este proyecto es open-source y está licenciado bajo MIT License.

---

✨ ¡Gracias por visitar! Si te gustó el proyecto, dame una ⭐️ para apoyarme.
