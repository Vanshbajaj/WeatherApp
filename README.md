Weather App - Android Jetpack Compose
Overview
A modern weather app built with Android Jetpack Compose, using Clean Architecture principles. This app demonstrates the use of Dependency Injection (DI), MVVM architecture, and best practices for Android development.
Architecture
This app follows Clean Architecture principles:

Presentation Layer: Includes UI components (Compose UI) and view models.
Domain Layer: Contains business logic and use cases.
Data Layer: Includes repositories, data sources (network and local), and models.
Tech Stack
Kotlin: The primary programming language.
Jetpack Compose: UI framework for building UIs in a declarative manner.
Hilt: Dependency Injection library to manage dependencies.
Retrofit: For HTTP requests.
Coroutines: For asynchronous programming.
ViewModel: For handling UI-related data in a lifecycle-conscious way.
Flow: To handle asynchronous streams of data.
