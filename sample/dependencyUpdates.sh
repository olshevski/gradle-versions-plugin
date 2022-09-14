#!/usr/bin/env sh
../gradlew -p .. publishToMavenLocal
../gradlew dependencyUpdates