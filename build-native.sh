#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021-26
# This software is licensed under MIT License
# See LICENSE
#

# Database Variables
export QUARKUS_DATASOURCE_DB_KIND='postgresql'
export QUARKUS_DATASOURCE_USERNAME='postgres'
export QUARKUS_DATASOURCE_PASSWORD='************'
export QUARKUS_DATASOURCE_JDBC_URL='jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:6543/postgres'

# JVM Variables
export QUARKUS_DEVSERVICES_ENABLED=false
export QUARKUS_PACKAGE_JAR_ENABLED=false
export QUARKUS_HIBERNATE_ORM_SCHEMA_MANAGEMENT_STRATEGY='drop-and-create'
export QUARKUS_HIBERNATE_ORM_SQL_LOAD_SCRIPT='import.sql'
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-21.jdk/Contents/Home
export JAVA_HOME=${GRAALVM_HOME}

./gradlew clean
./gradlew build -Dquarkus.native.enabled=true

