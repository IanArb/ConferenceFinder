import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm")
    distribution
    id("com.github.johnrengelman.shadow")
}

group = "com.ianarbuckle.conferencesapi"
version = "0.0.1"

application {
    mainClassName = "com.ianarbuckle.conferencesapi.ApplicationKt"
}

apply(plugin = "com.github.johnrengelman.shadow")

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")

    //Tests
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")

    //Mongo
    implementation("org.litote.kmongo:kmongo-coroutine:4.0.1")

    implementation("org.koin:koin-ktor:$koin_version")
    implementation("org.koin:koin-core:$koin_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("all")
    archiveBaseName.set("conferences-api")
    configurations = listOf(project.configurations.compile.get() ,project.configurations.runtimeClasspath.get())
    mergeServiceFiles()
    manifest {
        attributes["Main-Class"] = application.mainClassName
    }
}
