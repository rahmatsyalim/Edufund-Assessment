## About Project

This project made to complete assessment for Edufund Android Developer recruitment process. This implements 
clean architecture with MVVM design pattern. The output use data from https://dekontaminasi.com/ as the main resource.
This also implements offline first approach. Every data requested from API endpoints will be cached to local database
and then show the cached data to user as single source of truth. When device has no internet connection, 
the app will show related error message and then show the cached data as backup resources for better user experience.
UI loading state and empty state also handled in this project.

## App Contents

Screens :
* Home : Show Covid19 infection status list data from 34 province as Auto slide page. Also show current Covid19 news list.

* Search Hospital : Show Hospitals list data based on user search input.

* News  : Show news page in WebView when news list item clicked form Home screen.

## Build With

* Kotlin
* Coroutines
* Jetpack Compose UI
* Material Compose
* Navigation Compose
* Lifecycle ViewModel Compose
* Accompanist Utils (SystemUiController, Pager, SwipeRefresh, WebView)
* Dagger-Hilt
* Retrofit 2
* Gson converter
* Okhttp 3
* Room Database
* Timber logging
* Kotlin Gradle
* Version Catalogs