# 📱 AnimeApp

AnimeApp is a modular Android application built using modern development practices including Clean Architecture, Hilt for dependency injection, Room for local data storage, and Kotlin Coroutines with Flow for reactive programming. The app also utilizes dynamic feature modules for optimized delivery.

---

## 🧱 Architecture Overview

The project is structured using modularization and clean separation of concerns:


---

```
📦 AnimeApp/
├── app/                 # Main application module
├── core/                # Data, domain, repository, API, DB
├── common/              # Shared components and utilities
├── features/
│   ├── featureHome/     # Home screen: list of anime
│   ├── featureDetail/   # Detail screen: anime info
│   └── featureFavorite/ # Favorite screen: local database + dynamic delivery

```
---
## 🚀 Features

- Browse list of anime
- View detailed anime information
- Add and view favorite anime
- Dynamic Feature Module support for favorite screen

---
## 🛠️ Built With

| Tool/Library         | Function                             |
|----------------------|--------------------------------------|
| **Kotlin**           | Main programming language            |
| **Hilt**             | Dependency injection                 |
| **Room**             | Local database                       |
| **Retrofit + Gson**  | Network layer                        |
| **Kotlin Coroutines & Flow** | Asynchronous and reactive operations |
| **Navigation Component** | In-app navigation               |

---

## 🧑‍💻 How to Run

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/AnimeApp.git
cd AnimeApp
```
2. **Open the project in Android Studio**

3. **Sync Gradle**

4. **Run the app on a device or emulator running Android 8.0 (API 26) or higher**
