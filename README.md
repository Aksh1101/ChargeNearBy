# ⚡ ChargeNearBy

ChargeNearBy is a modern Android application that helps electric vehicle (EV) users discover nearby charging stations, view station details, and navigate to their destination with ease. Built using modern Android development practices, the project focuses on clean architecture, scalability, and an intuitive user experience.

> **Status:** 🚧 Active Development

---

## ✨ Features

### Current

* User Authentication
* Onboarding Experience
* Modern Jetpack Compose UI
* Material 3 Design
* Bottom Navigation
* Nested Navigation Architecture
* MVVM Architecture
* Dependency Injection with Hilt

### Upcoming

* Interactive Map using MapLibre
* Current Location Detection
* Nearby EV Charging Stations
* Search & Filters
* Station Details Bottom Sheet
* Navigation & Route Planning
* Favorites / Saved Stations
* Offline Caching
* Real-time Station Availability
* Dark & Light Theme Support

---

## 📱 Screens

* Splash Screen
* Onboarding
* Sign In
* Sign Up
* Home
* Map *(Coming Soon)*
* Saved Stations *(Coming Soon)*
* Profile *(Coming Soon)*

---

## 🏗️ Architecture

The project follows **Clean Architecture** with the **MVVM** pattern.

```text
Presentation (Jetpack Compose)
        │
        ▼
     ViewModel
        │
        ▼
    Repository
        │
   ┌────┴────┐
   ▼         ▼
Remote     Local
```

Project structure:

```text
app
├── core
├── data
├── di
├── domain
├── feature
│   ├── auth
│   ├── home
│   ├── map
│   ├── bookmark
│   ├── profile
│   └── station
├── navigation
└── ui
```

---

## 🛠️ Tech Stack

### Language

* Kotlin

### UI

* Jetpack Compose
* Material 3

### Architecture

* MVVM
* Clean Architecture

### Dependency Injection

* Hilt

### Navigation

* Navigation Compose
* Nested Navigation Graphs

### Asynchronous Programming

* Kotlin Coroutines
* StateFlow

### Maps

* MapLibre
* OpenStreetMap

### Location

* Fused Location Provider

### Networking *(Planned)*

* Retrofit
* Kotlin Serialization

### Local Storage *(Planned)*

* Room Database

---

## 🚀 Development Roadmap

### Sprint 1 ✅

* Project setup
* Hilt configuration
* Clean project structure

### Sprint 2 ✅

* Navigation architecture
* Bottom navigation
* Main application shell

### Sprint 3 🚧

* MapLibre integration
* User location
* Camera movement

### Sprint 4

* Charging station markers
* Custom marker icons

### Sprint 5

* Station details
* Bottom sheet

### Sprint 6

* Route navigation
* Distance & ETA

### Sprint 7

* Search
* Filters
* Nearby stations

### Sprint 8

* Bookmarks
* Offline support
* Performance improvements

---

## 🎯 Project Goals

* Build a production-ready Android application using modern development practices.
* Learn and implement scalable mobile architecture.
* Deliver a smooth and intuitive EV charging station discovery experience.
* Demonstrate clean, maintainable, and testable Android code.

---

## 📌 Project Status

ChargeNearBy is currently under active development. New features, improvements, and optimizations are being added incrementally as part of a structured sprint-based development process.

---

## 👨‍💻 Author

**Akshat Bhardwaj**

Aspiring Android Developer passionate about building high-quality mobile applications with Kotlin, Jetpack Compose, and modern Android architecture.

---

## ⭐ If you like this project

If you found this project interesting, consider giving it a ⭐ on GitHub. It helps support the project and motivates future development.
