# 🎬 MovieBrowser

**MovieBrowser** is a modern Android application for exploring popular movies, powered by the **TMDB API**. This project demonstrates the implementation of a clean architecture, reactive UI with Jetpack Compose, and local data persistence with Room.

---

## 🚀 Features

* **Discover Popular Movies**: Real-time fetching of the most popular titles from TMDB.
* **Detailed Insights**: View movie overviews, ratings, and release dates.
* **Personal Favorites**: Save movies to a local database for quick access.
* **Reactive UI**: Instant state synchronization across all screens using Kotlin Flow.
* **Modern Navigation**: Smooth transitions between screens using Compose Navigation.

---

## 🛠 Tech Stack

* **Language**: Kotlin
* **UI Framework**: Jetpack Compose
* **Architecture**: MVVM (ViewModel, StateFlow)
* **Networking**: Retrofit + Kotlinx Serialization
* **Local Database**: Room (using KSP for code generation)
* **Image Loading**: Coil
* **Asynchronous Programming**: Coroutines & Flow
* **Dependency Management**: Version Catalogs (`libs.versions.toml`)

---

## 📦 Getting Started

To run this project locally, you will need an API key from **The Movie Database (TMDB)**.

1.  Sign up at [themoviedb.org](https://www.themoviedb.org/) and generate your **API Key**.
2.  Clone the repository:
    ```bash
    git clone https://github.com/TheOver-svg/MovieBrowser.git)
    ```
3.  Open the `local.properties` file in your project's root directory and add your key:
    ```properties
    TMDB_API_KEY=your_api_key_here
    ```
4.  Build and run the project in Android Studio.

---

## 📂 Project Structure

* **`data/`**: Data models (DTOs), Entities for Room, and API service definitions.
* **`database/`**: Room configuration (Database, DAO).
* **`view/`**: UI components (Screens, Movie Cards, Theme, and Colors).
* **`viewmodel/`**: Logic for data processing and managing UI states.

---

## 📸 Screenshots

| Main Feed | Details View | Favorites |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/dde093ca-43c8-46af-80b0-d30240913630" width="200" /> | <img src="https://github.com/user-attachments/assets/c54a60cf-3f5f-49b3-9b14-24623a60a907" width="200" /> | <img src="https://github.com/user-attachments/assets/c4daf6f8-cfc4-4c15-8cdc-97fc46000cef" width="200" /> |
---

> **Note:** This project was developed for educational purposes, focusing on the latest Android development best practices, including Kotlin 2.0+ and KSP.
"""
