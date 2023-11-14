import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
}

kotlin {
  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
      }
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
      val coroutinesVersion = "1.7.3"
      val ktorVersion = "2.3.5"
      val sqlDelightVersion = "1.5.5"
      val dateTimeVersion = "0.4.1"

      androidMain.dependencies {
      implementation(libs.compose.ui)
      implementation(libs.compose.ui.tooling.preview)
      implementation(libs.androidx.activity.compose)
      implementation(libs.ktor.client.android)
      implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
    }

    commonMain.dependencies {
      implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.serialization.kotlinx.json)
      implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
      implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material)
      @OptIn(ExperimentalComposeLibrary::class)
      implementation(compose.components.resources)
    }

    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
      implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")

    }
  }
}

android {
  namespace = "com.alorma.gasstations"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    applicationId = "com.alorma.gasstations"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  dependencies {
    debugImplementation(libs.compose.ui.tooling)
  }
}

