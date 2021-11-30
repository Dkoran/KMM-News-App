import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val ktor_version = "1.6.4"
val realm_version = "0.6.0"
val coroutineVersion = "1.5.2-native-mt"

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("io.realm.kotlin") version "0.6.0"
    kotlin("plugin.serialization") version "1.5.31"
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "https://github.com/Kotlin/kotlin-with-cocoapods-sample"
        ios.deploymentTarget = "14.1"

        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
                implementation("io.realm.kotlin:library-base:$realm_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktor_version")
                //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktor_version")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }
}