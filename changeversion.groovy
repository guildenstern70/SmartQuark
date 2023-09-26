#!/usr/bin/env groovy
/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

OLD_VERSION = '0.4.1'
NEW_VERSION = '0.5.0'

def versionFiles = [
        './src/main/kotlin/net/littlelite/smartquark/Main.kt',
        './src/main/resources/application.yaml',
        'build.gradle.old'
]

println "Replacing version " + OLD_VERSION + " with version " + NEW_VERSION + "..."
versionFiles.each {
    println "-  Processing file " + it
    Files.replaceStringInFile(it, OLD_VERSION, NEW_VERSION)
}
versionFiles.each { Files.fileDelete(it + '.old') }
println "All done."

class Files {
    static boolean fileDelete(String filename) {
        return new File(filename).delete()
    }
    static void replaceStringInFile(String sourceFile,
                                    String replaceWhat,
                                    String replaceWith) {
        File fileSource = new File(sourceFile)
        fileSource.renameTo(sourceFile + '.old')
        File newFileSource = new File(sourceFile + '.old')
        File fileDest = new File(sourceFile)
        fileDest.withWriter { w ->
            newFileSource.eachLine { line ->
                w << line.replaceAll( replaceWhat, replaceWith ) + System.getProperty("line.separator")
            }
        }
    }
}
