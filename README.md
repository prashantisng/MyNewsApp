# NewsApp - Android MVVM News Application

A modern News application built with MVVM architecture using Android Jetpack components and latest tech-stack.

## Features

- Browse news articles from multiple sources
- Save articles for offline reading
- Search news by topics
- Share articles with others
- Clean and Modern UI

## Tech Stack & Libraries

- **Architecture Pattern:** MVVM (Model-View-ViewModel)
- **Programming Language:** Kotlin
- **Android Jetpack Components:**
  - Navigation Component
  - Room Database
  - ViewModel & LiveData
  - ViewBinding
- **Networking:** Retrofit2 & OkHttp3
- **Asynchronous Programming:** Coroutines
- **Image Loading:** Glide
- **API:** NewsAPI.org
## Architecture

The app follows MVVM architecture pattern:

```
├── data
│   ├── api
│   ├── db
│   └── repository
├── di
├── ui
│   ├── fragments
│   └── viewmodels
└── utils
``
