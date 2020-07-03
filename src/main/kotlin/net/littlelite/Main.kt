package net.littlelite

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.resource.spi.ConfigProperty

@QuarkusMain
open class Main : QuarkusApplication
{
    private val logger: Logger = LoggerFactory.getLogger(Main::class.java)
    private val version = "0.1.0"

    private fun hello()
    {
        logger.info("*****************************************************************")
        logger.info("  Smart Quark v.$version")
        logger.info("  JVM: " + System.getProperty("java.version"))
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

