#!/usr/bin/env sh
cd "$(dirname "${BASH_SOURCE[0]}")"
../gradlew -p .. publishToMavenLocal
../gradlew dependencyUpdates