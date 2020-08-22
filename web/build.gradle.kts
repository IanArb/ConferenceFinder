plugins {
    id("org.jetbrains.kotlin.js")
    kotlin("plugin.serialization")
}

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib-js"))

    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")

    implementation("org.jetbrains:kotlin-react:16.13.1-pre.112-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.112-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-router-dom:5.1.2-pre.110-kotlin-1.4.0")
    implementation(npm("react", "16.13.0"))
    implementation(npm("react-dom", "16.13.0"))

    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.112-kotlin-1.4.0")

    implementation(project(":common"))
}

kotlin {
    js {
        useCommonJs()
        browser()
    }
}