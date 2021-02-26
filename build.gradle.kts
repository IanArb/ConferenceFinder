buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha08")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}")
        classpath("com.github.jengelman.gradle.plugins:shadow:${Versions.shadowVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHiltVersion}")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://kotlin.bintray.com/kotlinx")
    }
}
