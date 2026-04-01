#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021-26
# This software is licensed under MIT License
# See LICENSE
#

# These commands build a Linux Executable file to be embedded into a container

# Database Variables needed for tests
export QUARKUS_DATASOURCE_JDBC_URL='postgresql://aws-0-eu-west-1.pooler.supabase.com:6543/postgres'
export QUARKUS_DATASOURCE_USERNAME='postgres'
export QUARKUS_DATASOURCE_PASSWORD='************'
export QUARKUS_HIBERNATE-ORM_SCHEMA-MANAGEMENT_STRATEGY=drop-and-create

# JVM Variables
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-21.3.0/Contents/Home
export JAVA_HOME=${GRAALVM_HOME}
./gradlew clean
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker

