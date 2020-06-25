plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
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
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.3.71")

                // Coroutines
                //Support kotlin/native blocking
                // Issue tracker: https://github.com/Kotlin/kotlinx.coroutines/issues/462
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5-native-mt")

                // Ktor
                implementation("io.ktor:ktor-client-core:1.3.2")
                implementation("io.ktor:ktor-client-json:1.3.2")
                implementation("io.ktor:ktor-client-logging:1.3.2")
                implementation("io.ktor:ktor-client-serialization:1.3.2")
                implementation("io.ktor:ktor-serialization:1.3.2")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")

            }
        }

        val androidMain by getting {
            dependencies {
                // Kotlin
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.71")

                // Ktor
                implementation("io.ktor:ktor-client-android:1.3.2")
                implementation("io.ktor:ktor-client-core-jvm:1.3.2")
                implementation("io.ktor:ktor-client-json-jvm:1.3.2")
                implementation("io.ktor:ktor-client-logging-jvm:1.3.2")
                implementation("io.ktor:ktor-client-serialization-jvm:1.3.2")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
            }
        }

        val jvmMain by getting {
            dependencies {
                // Kotlin
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.71")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")

                // Ktor
                implementation("io.ktor:ktor-server-core:1.3.2")

                implementation("io.ktor:ktor-client-core-jvm:1.3.2")
                implementation("io.ktor:ktor-client-json-jvm:1.3.2")
                implementation("io.ktor:ktor-client-logging-jvm:1.3.2")
                implementation("io.ktor:ktor-client-serialization-jvm:1.3.2")
                implementation("io.ktor:ktor-client-apache:1.3.2")
//                implementation(Ktor.slf4j)


                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")

            }
        }

        val iOSMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-ios:1.3.2")
                implementation("io.ktor:ktor-client-core-native:1.3.2")
                implementation("io.ktor:ktor-client-json-native:1.3.2")
                implementation("io.ktor:ktor-client-logging-native:1.3.2")
                implementation("io.ktor:ktor-client-serialization-native:1.3.2")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")

            }
        }

        val watchMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-ios:1.3.2")
                implementation("io.ktor:ktor-client-core-native:1.3.2")
                implementation("io.ktor:ktor-client-json-native:1.3.2")
                implementation("io.ktor:ktor-client-logging-native:1.3.2")
                implementation("io.ktor:ktor-client-serialization-native:1.3.2")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")

            }
        }

        val macOSMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-macosx64:1.3.2")


                // Ktor
                implementation("io.ktor:ktor-client-curl:1.3.2")
                implementation("io.ktor:ktor-client-core-macosx64:1.3.2")
                implementation("io.ktor:ktor-client-json-macosx64:1.3.2")
                implementation("io.ktor:ktor-client-logging-macosx64:1.3.2")
                implementation("io.ktor:ktor-client-serialization-macosx64:1.3.2")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-macosx64:0.20.0")

            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.5-native-mt")

                // ktor
                implementation("io.ktor:ktor-client-js:1.3.2")
                implementation("io.ktor:ktor-client-json-js:1.3.2")
                implementation("io.ktor:ktor-client-logging-js:1.3.2")
                implementation("io.ktor:ktor-client-serialization-js:1.3.2")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:0.20.0")
            }
        }
    }
}
