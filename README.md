## SmartQuark

![GitHub Actions](https://github.com/guildenstern70/SmartQuark/actions/workflows/gradle.yml/badge.svg)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8b6c1766de094783827f508e9aedf355)](https://www.codacy.com/gh/guildenstern70/SmartQuark/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=guildenstern70/SmartQuark&amp;utm_campaign=Badge_Grade)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A template project featuring Quarkus written in Kotlin.

### Development mode

    gradle quarkusDev

Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/

### Kotlin Version

Note that the Kotlin runtime version must meet the Quarkus-Kotlin plugin Kotlin version

    gradle dependencies | grep kotlin

### Packaging and running the application

The application can be packaged using 

    gradle quarkusBuild -Dquarkus.package.type=uber-jar

It produces the `smartquark-[version]-runner.jar` file in the `build` directory. Run with

    java -jar smartquark-[version]-runner.jar

### Creating and running in a Java Container

    gradle quarkusBuild
    docker build -f src/main/docker/Dockerfile.jvm -t guildenstern70/smartquarkus .
    docker run -i --rm -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" guildenstern70/smartquarkus

### Creating a native executable

Native version *cannot work with embedded H2 database*. It is recommended to create an 
external Postgres database and specify its coordinats as environment variables, as shown
in 'run-native-example.sh' script. 

First, download and install GraalVM CE Java 11 v.21.3 - the PostegreSQL JDBC driver at the moment
is not working with GraalVM CE Java 17 edition, you need the Java 11 edition.

    export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-21.3.0/Contents/Home

install native extensions

    cd /Library/Java/JavaVirtualMachines/graalvm-ce-java11-21.3.0/Contents/Home/bin
    ./gu install native-image

Now, you can create a native executable using: `./build-native`.
You can then execute your native executable with `run-native.sh` script.

### Creating a container 

Build Docker image using

    docker build --platform linux/amd64 -f src/main/docker/Dockerfile.jvm -t guildenstern70/smart-quark .

You can test it with

    docker run -i --rm -p 8080:8080 guildenstern70/smart-quark

### Creating a container with native executable

Unless you are using the same Linux OS of the target deployment machine, if you need to
deploy in Docker, you need also to "build" in Docker. Doing so, Docker requires a lot of resources.

Be sure to have Docker with at least 8 GB and 6 CPUs.

Also, you may adjust the XMX JVM value in 

    quarkus.native.native-image-xmx

property (application.yaml). This value should never be < 4g.

Prepare a Linux runnable exec and store in /build/smartquark-[version]-runner running a command
like:

    ./build-docker-native-example.sh

This command prepares also a specific Dockerfile inside 

    src/main/docker

To build the Docker image:
 
    docker build -f src/main/docker/Dockerfile.native -t guildenstern70/smart-quark .

To run it:

    ./run-docker-native

(the above script calls an environment file to pass needed environment variables)
