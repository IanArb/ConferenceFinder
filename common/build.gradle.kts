import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    // This is for iPhone emulator
    // Switch here to iosArm64 (or iosArm32) to build library for iPhone device
    val buildForDevice = project.findProperty("device") as? Boolean ?: false
    val iosTarget = if(buildForDevice) iosArm64("ios") else iosX64("ios")
    iosTarget.binaries {
        framework {
            // Disable bitcode embedding for the simulator build.
            if (!buildForDevice) {
                embedBitcode("disable")
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation( "org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")
                implementation("io.ktor:ktor-client-core:1.3.2")
                implementation("io.ktor:ktor-client-json:1.3.2")
                implementation("io.ktor:ktor-client-logging:1.3.2")
                implementation("io.ktor:ktor-client-serialization:1.3.2")
                implementation("io.ktor:ktor-serialization:1.3.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation( "org.jetbrains.kotlin:kotlin-stdlib-common")
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
            }
        }

        val androidTest by getting {
            dependencies {
//                implementation kotlin('test')
//                implementation kotlin('test-junit')
            }
        }
        val iosMain by getting {
        }
        val iosTest by getting {
        }
    }
}

//// This task attaches native framework built from ios module to Xcode project
//// (see iosApp directory). Don't run this task directly,
//// Xcode runs this task itself during its build process.
//// Before opening the project from iosApp directory in Xcode,
//// make sure all Gradle infrastructure exists (gradle.wrapper, gradlew).
//task copyFramework {
//    def buildType = project.findProperty('kotlin.build.type') ?: 'DEBUG'
//    def target = project.findProperty('kotlin.target') ?: 'ios'
//    dependsOn kotlin.targets."$target".binaries.getFramework(buildType).linkTask
//
//    doLast {
//        def srcFile = kotlin.targets."$target".binaries.getFramework(buildType).outputFile
//        def targetDir = getProperty('configuration.build.dir')
//        copy {
//            from srcFile.parent
//            into targetDir
//            include 'app.framework/**'
//            include 'app.framework.dSYM'
//        }
//    }
//}

tasks.register("copyFramework") {
    val buildType = project.findProperty("kotlin.build.type") as? String ?: "DEBUG"
    dependsOn("link${buildType.toLowerCase().capitalize()}FrameworkIos")

    doLast {
        val srcFile = (kotlin.targets["ios"] as KotlinNativeTarget).binaries.getFramework(buildType).outputFile
        val targetDir = project.property("configuration.build.dir")!!
                copy {
                    from(srcFile.parent)
                    into(targetDir)
                    include( "app.framework/**")
                    include("app.framework.dSYM")
                }
    }
}