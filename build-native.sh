#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021.
# This software is licensed under MIT License
# See LICENSE
#

# JVM Variables
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-21.1.0/Contents/Home
export JAVA_HOME=${GRAALVM_HOME}
./gradlew build -Dquarkus.package.type=native
