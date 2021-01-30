plugins {
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.+")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.+")
}

tasks.withType<Test> {
    useJUnitPlatform {}
}