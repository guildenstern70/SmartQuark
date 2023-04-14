/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
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

    override fun run(vararg args: String?): Int
    {
        logger.info("Running Quarkus")
        Quarkus.waitForExit()
        return 0
    }
}

