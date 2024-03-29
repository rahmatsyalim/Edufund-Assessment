@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
   id("dagger.hilt.android.plugin")
   kotlin("kapt")
}

val defVersionCode = 2
val defVersionName = "1.0.1"

android {
   namespace = "com.syalim.edufundtest"
   compileSdk = 33

   defaultConfig {
      applicationId = "com.syalim.edufundtest"
      minSdk = 21
      targetSdk = 33
      versionCode = defVersionCode
      versionName = defVersionName

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      vectorDrawables {
         useSupportLibrary = true
      }
   }

   buildTypes {
      getByName("release") {
         isMinifyEnabled = true
         proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
         // temporary signing config
         signingConfig = signingConfigs.getByName("debug")
      }
      getByName("debug") {
         applicationIdSuffix = ".debug"
      }
   }
   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }
   kotlinOptions {
      jvmTarget = JavaVersion.VERSION_1_8.toString()
   }
   buildFeatures {
      compose = true
   }
   composeOptions {
      kotlinCompilerExtensionVersion = "1.3.1"
   }
   packagingOptions {
      resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
   }
}

dependencies {

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.androidx.activity.compose)
   implementation(libs.androidx.compose.ui)
   implementation(libs.androidx.compose.ui.util)
   implementation(libs.androidx.compose.ui.tooling.preview)
   implementation(libs.androidx.compose.material)
   implementation(libs.accompanist.systemuicontroller)
   implementation(libs.accompanist.pager)
   implementation(libs.accompanist.pager.indicators)
   implementation(libs.accompanist.swiperefresh)
   implementation(libs.accompanist.webview)
   implementation(libs.androidx.lifecycle.livedata.ktx)
   implementation(libs.androidx.lifecycle.viewmodel.compose)
   implementation(libs.androidx.navigation.compose)
   implementation(libs.androidx.hilt.navigation.compose)
   implementation(libs.hilt.android)
   kapt(libs.hilt.android.compiler)
   implementation(libs.kotlinx.coroutines.android)
   implementation(libs.retrofit2.retrofit)
   implementation(libs.retrofit2.gson)
   implementation(libs.okhttp3.okhttp)
   implementation(libs.okhttp3.logging)
   implementation(libs.room.runtime)
   implementation(libs.room.ktx)
   kapt(libs.room.compiler)
   implementation(libs.timber)

   testImplementation(libs.junit4)
   androidTestImplementation(libs.androidx.test.ext)
   androidTestImplementation(libs.androidx.test.espresso.core)
   androidTestImplementation(libs.androidx.compose.ui.test.junit4)
   debugImplementation(libs.androidx.compose.ui.test.tooling)
   debugImplementation(libs.androidx.compose.ui.test.manifest)

}

tasks.register("getVersionName") {
   println(defVersionName)
}