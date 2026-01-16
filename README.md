# 🎮 FreeToGame Android App

![Kotlin](https://img.shields.io/badge/kotlin-100%25-blue?style=for-the-badge&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Melting%20Pot-4285F4?style=for-the-badge&logo=android)
![Platform](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android)
![License](https://img.shields.io/badge/license-MIT-black?style=for-the-badge)

A modern, native Android application built with **Jetpack Compose** that allows users to discover free-to-play games using the [FreeToGame API](https://www.freetogame.com/api-doc). The app demonstrates modern Android development practices, including MVVM architecture, Coroutines, Flow, and Material 3 Design.

## 📱 Screenshots

| Splash Screen | Home List | Search |
|:---:|:---:|:---:|
| <img src="screenshots/1.png" width="200"/> | <img src="screenshots/2.png" width="200"/> | <img src="screenshots/3.png" width="200"/> |

| Game Detail | Info & Specs | Gallery Mode |
|:---:|:---:|:---:|
| <img src="screenshots/4.png" width="200"/> | <img src="screenshots/5.png" width="200"/> | <img src="screenshots/6.png" width="200"/> |

## ✨ Features

* **Discover Games:** Browse a curated list of free-to-play games with infinite scrolling performance.
* **Search Functionality:** Real-time filtering to find specific games instantly.
* **Detailed View:** View game descriptions, screenshots, and system requirements.
* **Immersive Gallery:** Full-screen image slider with blur effects for game screenshots.
* **Dark Mode:** Fully optimized iOS-style Dark Theme for visual comfort.
* **Optimized Performance:** Image caching and lazy loading for smooth scrolling.

## 🛠 Tech Stack & Libraries

* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
* **Architecture:** MVVM (Model-View-ViewModel) + Clean Architecture Principles
* **Network:** [Retrofit2](https://square.github.io/retrofit/) & [OkHttp3](https://square.github.io/okhttp/)
* **Concurrency:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
* **Image Loading:** [Coil](https://coil-kt.github.io/coil/) (Caching & Performance optimized)
* **Navigation:** [Compose Navigation](https://developer.android.com/guide/navigation/navigation-compose)
* **Data Serialization:** [Gson](https://github.com/google/gson)

## 🏗 Architecture

The application follows the **MVVM (Model-View-ViewModel)** architectural pattern to ensure separation of concerns and testability.

* **Data Layer:** Handles API calls and data mapping (Retrofit, Repository Pattern).
* **Domain Layer:** Contains business logic and pure data models.
* **UI Layer:** Composable functions observing ViewModel state (StateFlow).

## 🚀 How to Run

1.  Clone the repository:
    ```bash
    git clone [https://github.com/ugurrdev/FreeToGame.git](https://github.com/ugurrdev/FreeToGame.git)
    ```
2.  Open the project in **Android Studio**.
3.  Sync Gradle files.
4.  Run the app on an Emulator or Physical Device.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
**Developed with ❤️ by [Uğur Pişkin (ugurrdev)](https://github.com/ugurrdev)**