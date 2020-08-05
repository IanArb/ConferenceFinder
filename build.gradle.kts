buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}")
        classpath("com.github.jengelman.gradle.plugins:shadow:${Versions.shadowVersion}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navAndroidGradle}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerAndroidVersion}")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    }
}
