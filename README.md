# Gradle Versions Plugin

Sets up a simple version selection strategy for Ben Manes' [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin): only more stable versions will be suggested for updates **(alpha -> beta -> rc -> stable)**. If the version stability cannot be recognized it will be suggested anyway, so you can decide for yourself if the version should be used.

To use it just add:

```kotlin
plugins {
    id("dev.olshevski.versions") version "1.0.3"
}
```