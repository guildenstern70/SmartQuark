#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021-23
# This software is licensed under MIT License
# See LICENSE
#

# JVM Variables
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.3.1/Contents/Home
export JAVA_HOME=${GRAALVM_HOME}
./gradlew clean
./gradlew build -Dquarkus.package.type=native
