#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021-22
# This software is licensed under MIT License
# See LICENSE
#

# These commands build a Linux Executable file to be embedded into a container

# Database Variables needed for tests
export DB_KIND=postgresql
export DB_URL=jdbc:postgresql://****
export DB_USERNAME=****
export DB_PASSWORD=****

# JVM Variables
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-21.3.0/Contents/Home
export JAVA_HOME=${GRAALVM_HOME}
./gradlew clean
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker

