import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `maven-publish`
    signing
    kotlin("jvm") version "1.8.10"
    id("com.gradle.plugin-publish") version "1.1.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    withType<KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
    }
    test {
        useJUnitPlatform()
    }
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.46.0")

    val junitVersion = "5.9.0"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("com.google.truth:truth:1.1.3")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

@Suppress("UnstableApiUsage")
gradlePlugin {
    website.set("https://github.com/olshevski/gradle-versions-plugin")
    vcsUrl.set("https://github.com/olshevski/gradle-versions-plugin")
    plugins {
        create("versionsPlugin") {
            id = "dev.olshevski.versions"
            implementationClass = "dev.olshevski.versions.VersionsPlugin"
            displayName = "Gradle Versions Plugin"
            description = "Default configuration for Ben Manes' Gradle Versions Plugin"
            tags.set(listOf("dependencies", "versions", "updates"))
        }
    }
}

publishing {
    publications {
        withType(MavenPublication::class.java).all {
            pom {
                name.set("Gradle Versions Plugin")
                description.set("Default configuration for Ben Manes' Gradle Versions Plugi")
                url.set("https://github.com/olshevski/gradle-versions-plugin")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/olshevski/gradle-versions-plugin/blob/main/LICENSE")
                    }
                }

                developers {
                    developer {
                        id.set("olshevski")
                        name.set("Vitali Olshevski")
                        email.set("tech@olshevski.dev")
                        url.set("https://olshevski.dev")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/olshevski/gradle-versions-plugin.git")
                    developerConnection.set("scm:git:https://github.com/olshevski/gradle-versions-plugin.git")
                    url.set("https://github.com/olshevski/gradle-versions-plugin")
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        project.properties["signing.key"].toString(),
        project.properties["signing.password"].toString(),
    )
}