buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.71")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.3.71")
        classpath("com.github.jengelman.gradle.plugins:shadow:5.2.0")
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
