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
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private const val APP_VERSION = "0.3.1"

@QuarkusMain
open class Main : QuarkusApplication
{
    private val logger: Logger = LoggerFactory.getLogger(Main::class.java)

    override fun run(vararg args: String?): Int
    {
        Quarkus.waitForExit()
        return 0
    }

    companion object
    {
        @JvmField
        var VERSION = APP_VERSION
    }
}

