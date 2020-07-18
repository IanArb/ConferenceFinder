plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("org.jetbrains.kotlin.native.cocoapods")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
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
}

kotlin {
    targets {
        val sdkName: String? = System.getenv("SDK_NAME")

        val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
        if (isiOSDevice) {
            iosArm64("iOS64")
        } else {
            iosX64("iOS")
        }

        val isWatchOSDevice = sdkName.orEmpty().startsWith("watchos")
        if (isWatchOSDevice) {
            watchosArm64("watch")
        } else {
            watchosX86("watch")
        }

        macosX64("macOS")
        android()
        jvm()
        js {
            browser()
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
                // Kotlin
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlinVersion}")

                // Coroutines
                //Support kotlin/native blocking
                // Issue tracker: https://github.com/Kotlin/kotlinx.coroutines/issues/462
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutinesVersion}")

                // Ktor
                implementation("io.ktor:ktor-client-core:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-serialization:${Versions.ktorVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinxSerializationVersion}")

            }
        }

        val androidMain by getting {
            dependencies {
                // Kotlin
                implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}")

                // Ktor
                implementation("io.ktor:ktor-client-android:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-core-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization-jvm:${Versions.ktorVersion}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerializationVersion}")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesAndroidVersion}")
            }
        }

        val jvmMain by getting {
            dependencies {
                // Kotlin
                implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")

                // Ktor
                implementation("io.ktor:ktor-server-core:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-core-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization-jvm:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-apache:${Versions.ktorVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerializationVersion}")

            }
        }

        val iOSMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-ios:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-core-native:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json-native:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging-native:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization-native:${Versions.ktorVersion}")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutinesVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.kotlinxSerializationVersion}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerializationVersion}")

            }
        }

        val watchMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-ios:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-core-native:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json-native:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging-native:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization-native:${Versions.ktorVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.kotlinxSerializationVersion}")

            }
        }

        val macOSMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-macosx64:${Versions.coroutinesVersion}")


                // Ktor
                implementation("io.ktor:ktor-client-curl:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-core-macosx64:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json-macosx64:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging-macosx64:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization-macosx64:${Versions.ktorVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-macosx64:${Versions.kotlinxSerializationVersion}")

            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutinesVersion}")

                // ktor
                implementation("io.ktor:ktor-client-js:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-json-js:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging-js:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization-js:${Versions.ktorVersion}")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.kotlinxSerializationVersion}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerializationVersion}")
            }
        }
    }
}
