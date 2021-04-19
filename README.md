# SmartQuark

[![Build Status](https://travis-ci.com/guildenstern70/SmartQuark.svg?branch=master)](https://travis-ci.com/guildenstern70/SmartQuark)


If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Development mode

    gradle quarkusDev

### Kotlin Version

Note that the Kotlin runtime version must met the Quarkus-Kotlin plugin Kotlin version (now it's 1.4.20)

    gradle dependencies | grep kotlin

### Packaging and running the application

The application can be packaged using 

    gradle quarkusBuild -Dquarkus.package.type=uber-jar

It produces the `smartquark-[version]-runner.jar` file in the `build` directory. Run with

    java -jar smartquark-[version]-runner.jar

### Creating a native executable

You can create a native executable using: `./build-native`.
You can then execute your native executable with: `./build/smartquark-[version]-runner`

### Creating a Docker native executable

Unless you are using the same Linux OS of the target deployment machine, if you need to
deploy in Docker, you need also to "build" in Docker. Doing so, it requires a lot of resources
to give to your Docker environment.

Be sure to have Docker with at least 8 GB and 6 CPUs.

Also, you may adjust the XMX JVM value in 

    quarkus.native.native-image-xmx

property (application.yaml). This value should never be < 4g.

Prepare a Linux runnable exec and store in /build/smartquark-010-runner with this command:

    gradle build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

This command prepares also a specific Dockerfile inside 

    src/main/docker

To build the Docker image:
 
    docker build -f src/main/docker/Dockerfile.native -t guildenstern70/smart-quark .

To run it:

    ./run-docker-native

(the above script calls an environment file to pass needed environment variables)

    
    
    