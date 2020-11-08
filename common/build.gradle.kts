plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("org.jetbrains.kotlin.native.cocoapods")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "30.0.2"
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

kotlin {
    targets {
        val sdkName: String? = System.getenv("SDK_NAME")

        val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
        if (isiOSDevice) {
            iosArm64("iOS64")
        } else {
            iosX64("iOS")
        }

        macosX64("macOS")
        android()
    }

    js {
        browser {

        }
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                //Support kotlin/native blocking
                // Issue tracker: https://github.com/Kotlin/kotlinx.coroutines/issues/462
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}")

                // Ktor
                implementation("io.ktor:ktor-client-core:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktorVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerializationVersion}")

            }
        }

        val androidMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-android:${Versions.ktorVersion}")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesAndroidVersion}")
            }
        }

        val iOSMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-ios:${Versions.ktorVersion}")
            }
        }

        val macOSMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-core-macosx64:${Versions.ktorVersion}")
            }
        }

        val jsMain by getting {
            dependencies {
                // ktor
                implementation("io.ktor:ktor-client-js:${Versions.ktorVersion}")
            }
        }
    }
}
