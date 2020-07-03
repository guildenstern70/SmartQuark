#!/usr/bin/env bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
./gradlew build -Dquarkus.package.type=native
