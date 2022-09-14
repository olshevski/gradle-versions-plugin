plugins {
    kotlin("jvm") version "1.7.10"
    `java-gradle-plugin`
    `maven-publish`
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
}

gradlePlugin {
    plugins {
        create("versions") {
            id = "dev.olshevski.versions"
            implementationClass = "dev.olshevski.versions.VersionsPlugin"
        }
    }
}