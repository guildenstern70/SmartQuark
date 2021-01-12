# SmartQuark

[![Build Status](https://travis-ci.com/guildenstern70/SmartQuark.svg?branch=master)](https://travis-ci.com/guildenstern70/SmartQuark)


If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Development mode

    gradle quarkusDev

### Kotlin Version

Note that the Kotlin runtime version must met the Quarkus-Kotlin plugin Kotlin version (now it's 1.3.72)

    gradle dependencies | grep kotlin

### Packaging and running the application

The application can be packaged using 

    gradle quarkusBuild

It produces the `smartquark-0.1.0-runner.jar` file in the `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.
The application is now runnable using `java -jar build/smartquark-0.1.0-runner.jar`.
If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:

    gradle quarkusBuild --uber-jar

### Creating a native executable

You can create a native executable using: `./build-native`.
You can then execute your native executable with: `./build/smartquark-0.1.0-runner`

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

    
    
    