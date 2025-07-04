/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service;

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import net.littlelite.smartquark.config.SmartQuark
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@ApplicationScoped
class StartupService
{
    private val logger: Logger = LoggerFactory.getLogger(StartupService::class.java)

    @Inject
    @field: Default
    lateinit var dbInitializer: DBInitializer

    @Inject
    lateinit var smartquark: SmartQuark

    private fun hello()
    {
        logger.info("*****************************************************************")
        logger.info("  SmartQuark v.${smartquark.version()}")
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

