/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import net.littlelite.smartquark.config.SmartQuark
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@ApplicationScoped
class StartupService(private val dbInitializer: DBInitializer,
                           private val smartQuark: SmartQuark)
{
    private val logger: Logger = LoggerFactory.getLogger(StartupService::class.java)

    private fun hello()
    {
        logger.info("*****************************************************************")
        logger.info("  SmartQuark v.${this.smartQuark.version()}")
        logger.info("  JVM: " + System.getProperty("java.vendor") + " " + System.getProperty("java.version"))
        logger.info("  Listening on: http://localhost:8080")
        logger.info("*****************************************************************")
    }

    fun onStart(@Observes ev: StartupEvent?)
    {
        logger.info("Starting up application")
        this.dbInitializer.populateDB()
        this.hello()
    }
}

