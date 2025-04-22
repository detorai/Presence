import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.sqldelight)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.presenceapp.composeapp.resources"
    generateResClass = auto
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
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

        iosTarget.compilations.getByName("main") {
            cinterops {
                val uikit by creating {
                    defFile(file("src/iosMain/c_interop/Uikit.def"))
                }
            }
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.datastore.preferences)
                implementation(libs.androidx.datastore.preferences.core)
                implementation(libs.androidx.startup.runtime)

                // ktor
                implementation(libs.ktor.client.okhttp)

                // koin
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
                implementation(libs.koin.ktor)

                // sql
                implementation(libs.android.driver)
            }
        }

        val commonMain by getting {
            resources.srcDirs("src/commonMain/composeResources")
            dependencies {
                implementation(libs.runtime)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)

                // voyager
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.screenmodel)
                implementation(libs.voyager.koin)

                // ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.websockets)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.serialization.kotlinx.json)

                // ktorfit
                implementation(libs.ktorfit)

                // koin
                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // logging
                implementation(libs.kotlin.logging)

                // settings
                implementation(libs.multiplatform.settings)
            }
        }

        iosMain.dependencies {
            // ktor
            implementation(libs.ktor.client.darwin)

            // koin
            implementation(libs.koin.core.native)

            // sql
            implementation(libs.native.driver)

            implementation(libs.kotlinx.coroutines.core.v173nativemt)
            implementation(libs.kermit)
        }
    }
}

android {
    namespace = "org.example.presenceapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.presenceapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.androidx.ui.text.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.foundation.layout.android)
    debugImplementation(compose.uiTooling)

    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspIosX64", libs.ktorfit.ksp)
    add("kspIosArm64", libs.ktorfit.ksp)
}

sqldelight {
    databases {
        create("PresenceDatabase") {
            packageName.set("org.example.presenceapp.data.local.sql.cache")
        }
    }
}