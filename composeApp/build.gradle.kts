import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.incremental.deleteDirectoryContents
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.appdistribution)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

val keystoreProperties = Properties().apply {
    load(rootProject.file("keystore.properties").inputStream())
}
val lApplicationId = libs.versions.applicationId
    .get()
val lApplicationVersion = libs.versions.applicationVersion
    .get()
val lApplicationVersionCode = libs.versions.applicationVersion
    .get()
    .replace(".", "")
    .toInt()

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(compose.preview)

            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.camera.camera2)
            implementation(libs.androidx.camera.core)
            implementation(libs.androidx.camera.extensions)
            implementation(libs.androidx.camera.lifecycle)
            implementation(libs.androidx.camera.view)
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            // implementation(libs.firebase.admin)
            implementation(libs.coil3.okhttp)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.auth)
            implementation(libs.firebase.crashlytics)
            // implementation(libs.firebase.mpp.auth)
            // implementation(libs.firebase.performance)
            implementation(libs.firebase.ui)
            implementation(libs.google.id)
            implementation(libs.koin.android)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.okhttp)
            implementation(libs.mlkit.barcode)
            implementation(libs.play.services.auth)
            implementation(libs.play.services.scanner)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.runtime)
            implementation(compose.ui)

            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.coil3.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.serialization.json)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.coil3.jvm)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.apache5)
        }
        wasmJsMain.dependencies {
            implementation(npm("firebase", "10.14.1"))
        }
    }
}

android {
    namespace = lApplicationId
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    sourceSets["debug"].res.srcDirs("src/androidMain/res", "src/commonMain/composeResources")

    defaultConfig {
        applicationId = lApplicationId
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = lApplicationVersionCode
        versionName = lApplicationVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            firebaseAppDistribution {
                artifactPath =
                    "./composeApp/build/outputs/apk_from_bundle/debug/composeApp-debug-universal.apk"
                artifactType = "APK"
                releaseNotesFile = "./composeApp/release_notes.txt"
                testers = "acsgsa92@gmail.com"
            }
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
        debugImplementation(compose.components.uiToolingPreview)
    }
}

compose.desktop {
    application {
        mainClass = "$lApplicationId.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = lApplicationId
            packageVersion = "1.0.0"
        }
    }
}

val buildConfigGenerator by tasks.registering(Sync::class) {
    from(
        resources.text.fromString(
            """
            |package $lApplicationId.generated
            |
            |object BuildConfig {
            |   const val APPLICATION_ID = "$lApplicationId"
            |   const val APPLICATION_VERSION = "$lApplicationVersion"
            |   const val APPLICATION_VERSION_CODE = "$lApplicationVersionCode"
            |
            |   const val DEBUG = true
            |
            |   const val FIREBASE_AUTH_DOMAIN = "${keystoreProperties["firebaseAuthDomain"]}"
            |   const val FIREBASE_AUTH_API_HOST_IDENTIFY = "https://identitytoolkit.googleapis.com/v1"
            |   const val FIREBASE_AUTH_API_HOST_TOKEN = "https://securetoken.googleapis.com/v1"
            |   const val FIREBASE_APP_ID = "${keystoreProperties["firebaseAppId"]}"
            |   const val FIREBASE_API_KEY = "${keystoreProperties["firebaseApiKey"]}"
            |   const val FIREBASE_MEASUREMENT_ID = "${keystoreProperties["firebaseMeasurementId"]}"
            |   const val FIREBASE_MESSAGING_SENDER_ID = "${keystoreProperties["firebaseMessagingSenderId"]}"
            |   const val FIREBASE_PROJECT_ID = "${keystoreProperties["firebaseProjectId"]}"
            |   const val FIREBASE_STORAGE_BUCKET = "${keystoreProperties["firebaseStorageBucket"]}"
            |   const val FIREBASE_WEB_API_KEY = "${keystoreProperties["firebaseWebApiKey"]}"
            |
            |}
            |
            """.trimMargin()
        )
    ) {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        rename { "BuildConfig.kt" } // set the file name FIREBASE_AUTH_API_TOKEN_HOST
        into("") // change the directory to match the package
    }

    // the target directory
    into(
        layout.projectDirectory.dir(
            "src/commonMain/kotlin/${
                lApplicationId.replace(
                    ".",
                    "/"
                )
            }/generated/"
        )
    )
}

val moveBuiltFiles by tasks.registering(Sync::class) {
    val destination = layout.projectDirectory.dir(
        "../dist/"
    )
    try {
        destination.asFile.deleteDirectoryContents()
    } catch (_: Throwable) {
        // Do nothing
    }
    from(
        layout.projectDirectory.dir(
            "build/dist/wasmJs/productionExecutable/"
        )
    )
    into(
        destination
    )
}
