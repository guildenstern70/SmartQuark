/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import net.littlelite.smartquark.dto.PhoneDTO
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class DBInitializer(
        @ConfigProperty(name= "quarkus.datasource.jdbc.url")
        private val dbUrl: String
)
{
    private val logger: Logger = LoggerFactory.getLogger(DBInitializer::class.java)

    @Inject
    lateinit var personService: PersonService

    fun populateDB()
    {
        if (this.personService.getPersonCount() == 0L)
        {
            logger.info("Populating DB $dbUrl")
            val alessioPhones = setOf(
                PhoneDTO("348", "39290022"),
                PhoneDTO("333", "32233232")
            )
            val renzoPhones = setOf(
                PhoneDTO("348", "12809128")
            )
            val elenaPhones = setOf(
                PhoneDTO("349", "23223323"),
                PhoneDTO("334", "32332232")
            )
            this.personService.addPerson("Alessio", "Saltarin", 50, alessioPhones)
            this.personService.addPerson("Renzo", "Piano", 99, renzoPhones)
            this.personService.addPerson("Elena", "Zambrelli", 25, elenaPhones)
            logger.info("Done.")
        }
    }

}
