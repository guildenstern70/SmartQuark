#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021-25
# This software is licensed under MIT License
# See LICENSE
#

# JVM Variables
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-jdk-17.0.8+9.1/Contents/Home
export JAVA_HOME=${GRAALVM_HOME}
./gradlew clean
./gradlew build -Dquarkus.package.type=native
