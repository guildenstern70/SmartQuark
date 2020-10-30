package net.littlelite.service;

import io.quarkus.runtime.StartupEvent
import io.quarkus.runtime.configuration.ProfileManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.enterprise.inject.Default
import javax.inject.Inject


@ApplicationScoped
class StartupService
{
    private val logger: Logger = LoggerFactory.getLogger(StartupService::class.java)

    @Inject
    @field: Default
    lateinit var dbInitializer: DBInitializer

    fun onStart(@Observes ev: StartupEvent?)
    {
        logger.info("Starting up application")
        logger.info("The application is starting with profile " + ProfileManager.getActiveProfile());
        this.dbInitializer.populateDB()
    }
}
