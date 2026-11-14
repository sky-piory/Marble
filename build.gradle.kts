plugins {
    kotlin("jvm") version "2.4.21"
    id("com.gradleup.shadow") version "9.2.2"
}
group = "kr.marble"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}
dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    implementation("org.xerial:sqlite-jdbc:3.50.3.0")
}
// Paper API 26.3 requires Java 25; Kotlin 2.4.21 supports JVM target 25.
kotlin {
    jvmToolchain(25)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }
}
tasks {
    shadowJar {
        archiveClassifier.set("")
        relocate("org.sqlite", "kr.marble.libs.sqlite")
    }
    build { dependsOn(shadowJar) }
}
