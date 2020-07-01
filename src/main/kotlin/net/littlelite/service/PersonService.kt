package net.littlelite.service

import net.littlelite.dao.PersonDAO
import net.littlelite.dto.PersonDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.persistence.EntityManager

@ApplicationScoped
class PersonService
{
    @Inject
    @field: Default
    lateinit var em: EntityManager

    @Inject
    lateinit var personDAO: PersonDAO

    private val logger: Logger = LoggerFactory.getLogger(PersonService::class.java)

    fun getAllPersons() : List<PersonDTO> =
            this.personDAO.listAll().map { it -> PersonDTO.fromPerson(it) }

}