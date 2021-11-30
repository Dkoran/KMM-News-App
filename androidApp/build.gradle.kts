plugins {
    id("com.android.application")
    kotlin("android")
    id( "androidx.navigation.safeargs.kotlin")
    id ("kotlin-android-extensions")


}
val coroutineVersion = "1.5.2"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    compileOnly("io.realm.kotlin:library:0.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
    implementation("io.coil-kt:coil:1.4.0")
    implementation("com.google.code.gson:gson:2.8.9")
    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    // Navigation Components
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.5")

}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.dkoran.newsappkmm.android"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}