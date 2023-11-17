import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.serialization)
  alias(libs.plugins.sqldelight)
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
      isStatic = false
      binaryOption("bundleId", "com.alorma.gasstations")
      linkerOpts.add("-lsqlite3")
    }
  }

  sourceSets {

    commonMain.dependencies {
      implementation(libs.kotlinx.coroutines.core)

      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.serialization.kotlinx.json)
      implementation(libs.ktor.logging)

      implementation(libs.sqldelight.runtime)

      implementation(libs.kotlinx.datetime)

      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.animation)
      implementation(compose.material3)
      implementation(compose.materialIconsExtended)
      @OptIn(ExperimentalComposeLibrary::class)
      implementation(compose.components.resources)

      api(libs.precompose)
      api(libs.precompose.viewmodel)
      api(libs.precompose.koin)

      implementation(libs.napier)

      implementation(libs.multiplatform.settings)
      implementation(libs.multiplatform.settings.coroutines)
      implementation(libs.multiplatform.settings.noarg)

      implementation(project.dependencies.platform(libs.koin.bom))

      api(libs.koin.core)
      api(libs.koin.core.coroutines)
      api(libs.koin.compose)
    }

    androidMain.dependencies {
      implementation(libs.compose.ui)
      implementation(libs.compose.ui.tooling.preview)
      implementation(libs.androidx.activity.compose)
      implementation(libs.ktor.client.android)
      implementation(libs.sqldelight.android.driver)
      implementation(libs.koin.android)
    }

    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
      implementation(libs.sqldelight.native.driver)
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
      excludes += "/META-INF/versions/9/previous-compilation-data.bin"
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

sqldelight {
  databases {
    create("AppDatabase") {
      packageName.set("com.alorma.gasstations.cache")
    }
  }
  linkSqlite = true
}



