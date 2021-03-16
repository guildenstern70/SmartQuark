/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@QuarkusMain
open class Main : QuarkusApplication
{
    private val logger: Logger = LoggerFactory.getLogger(Main::class.java)
    private val version = "0.2.0"

    private fun hello()
    {
        logger.info("*****************************************************************")
        logger.info("  SmartQuark v.$version")
        logger.info("  JVM: " + System.getProperty("java.vendor") + " " + System.getProperty("java.version"))
        logger.info("  Listening on: http://localhost:8080")
        logger.info("*****************************************************************")
    }

    override fun run(vararg args: String?): Int
    {
        hello()
        Quarkus.waitForExit()
        return 0
    }
}

