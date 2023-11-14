import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  id("com.android.library")
  alias(libs.plugins.jetbrainsCompose)
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

kotlin {
  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  targetHierarchy.default()

  withSourcesJar()

  androidTarget {
    publishLibraryVariants("release")
    compilations.all {
      kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
      }
    }
  }

  jvm("desktop")

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
      binaryOption("bundleId", "com.alorma.material3.theme.library")
    }
  }

  sourceSets {
    val androidMain by getting {
      dependencies {
        implementation(libs.compose.ui)
        implementation(libs.compose.ui.tooling.preview)
        implementation(libs.androidx.activity.compose)
      }
    }
    val desktopMain by getting {
      dependencies {
        implementation(compose.desktop.currentOs)
      }
    }
    val commonMain by getting {
      dependencies {
        implementation(compose.material3)

        implementation(compose.runtime)
        implementation(compose.foundation)
        @OptIn(ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)
      }
    }
  }
}

android {
  namespace = "com.alorma.material3.theme.library"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
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
  lint {
    checkReleaseBuilds = false
    abortOnError = false
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

compose.desktop {
  application {
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "com.alorma.material3.theme.library"
      packageVersion = "1.0.0"
    }
  }
}
