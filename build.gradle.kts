plugins {
    `java-gradle-plugin`
    `maven-publish`
    kotlin("jvm") version "1.7.10"
    id("com.gradle.plugin-publish") version "1.0.0-rc-1"
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")

    val junitVersion = "5.9.0"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("com.google.truth:truth:1.1.3")
}

gradlePlugin {
    plugins {
        create("versionsPlugin") {
            id = "dev.olshevski.versions"
            implementationClass = "dev.olshevski.versions.VersionsPlugin"
            displayName = "Gradle Versions Plugin"
            description = "Default configuration for Ben Manes' Gradle Versions Plugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/olshevski/gradle-versions-plugin"
    vcsUrl = "https://github.com/olshevski/gradle-versions-plugin"
    tags = listOf("dependencies", "versions", "updates")
}