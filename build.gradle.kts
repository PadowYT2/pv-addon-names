plugins {
    java
    id("fabric-loom") version "1.10-SNAPSHOT" 
}

group = "ru.padow"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.toVersion(21)
    targetCompatibility = JavaVersion.toVersion(21)

    disableAutoTargetJvm()
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net")
    maven("https://repo.plasmoverse.com/releases")
    maven("https://repo.plasmoverse.com/snapshots")
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings("net.fabricmc:yarn:1.20.1+build.10:v2")
    modImplementation("net.fabricmc:fabric-loader:0.16.14")
    compileOnly("su.plo.voice.api:server:2.1.5-SNAPSHOT")
    modImplementation("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    modImplementation("io.github.revxrsal:lamp.fabric:4.0.0-rc.12")
    include("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    include("io.github.revxrsal:lamp.fabric:4.0.0-rc.12")    
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
}
