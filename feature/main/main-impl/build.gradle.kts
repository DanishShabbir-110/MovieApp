plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.main_impl"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    //Serializable
    implementation(libs.kotlinx.serialization.json)

    // Navigation 3
    implementation(libs.navigation3.runtime)
    implementation(libs.navigation3.ui)

    //Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.icons.extended)

    //Modules
    implementation(projects.feature.main.mainApi)
    implementation(projects.core.navigation)
    implementation(projects.core.shared)
    implementation(projects.feature.home.homeApi)
    implementation(projects.domain)

    //Paging 3
    implementation(libs.androidx.paging.compose)

}