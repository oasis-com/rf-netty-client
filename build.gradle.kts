plugins {
    kotlin("jvm") version "1.6.10"
}

group = "com.oasis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.netty:netty-all:4.1.72.Final")
    implementation("com.google.protobuf:protobuf-java:3.19.1")
}