package net.littlelite.service

import net.littlelite.model.Person
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional
import kotlin.random.Random

@ApplicationScoped
class DBInitializer(
        @ConfigProperty(name= "quarkus.datasource.jdbc.url")
        private val dbUrl: String
)
{
    private val logger: Logger = LoggerFactory.getLogger(DBInitializer::class.java)

    @Inject
    @field: Default
    lateinit var em: EntityManager

    @Transactional
    fun populateDB()
    {
        logger.info("Populating DB $dbUrl")
        this.createPerson("Alessio", "Saltarin")
        this.createPerson("Renzo", "Piano")
        this.createPerson("Elena", "Zambrelli")
        logger.info("Done.")
    }

    @Transactional
    fun createPerson(name: String, surname: String)
    {
        val age = Random.nextInt(1, 100)
        val person = Person(name, surname, age)
        em.persist(person)
    }
}
