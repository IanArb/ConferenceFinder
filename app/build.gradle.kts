import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId("com.ianarbuckle.conferencesfinder")
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    val keysProperties = Properties()
    keysProperties.load(project.rootProject.file("local.properties").inputStream())
    val apiKey = keysProperties["google.map.key"] as String

    buildTypes {
        getByName("debug") {
            resValue("string", "google_maps_key", apiKey)
            isMinifyEnabled = false
        }

        getByName("release") {
            resValue("string", "google_maps_key", apiKey)
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta01"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {

    //Androidx
    implementation("androidx.core:core-ktx:${Versions.coreVersion}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}")
    implementation("androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtVersion}")
    implementation("androidx.activity:activity-ktx:${Versions.activityKtVersion}")

    implementation("com.jakewharton.threetenabp:threetenabp:${Versions.threetenabpVersion}")

    implementation("com.google.android.material:material:${Versions.materialVersion}")
    implementation("androidx.ui:ui-tooling:1.0.0-alpha07")

    testImplementation("junit:junit:4.13")

    //Dagger
    implementation("com.google.dagger:dagger:${Versions.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:${Versions.daggerHiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.daggerHiltVersion}")
    kapt("androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltCompilerVersion}")

    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")
    annotationProcessor("com.github.bumptech.glide:compiler:${Versions.glideVersion}")

    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}")

    implementation("com.google.android.gms:play-services-maps:${Versions.playServicesVersion}")

    implementation("androidx.compose.ui:ui:${Versions.composeVersion}")
    implementation("androidx.compose.ui:ui-graphics:${Versions.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.composeVersion}")
    implementation("androidx.compose.foundation:foundation-layout:${Versions.composeVersion}")
    implementation("androidx.compose.material:material:${Versions.composeVersion}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}")
    implementation("androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}")
    implementation("androidx.paging:paging-compose:${Versions.pagingComposeVersion}")
    implementation("androidx.activity:activity-compose:${Versions.composeVersion}")

    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.0")


    implementation(project(":common"))
}
