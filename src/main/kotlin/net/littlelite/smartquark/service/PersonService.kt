/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import net.littlelite.smartquark.dao.PersonDAO
import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.model.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional


@ApplicationScoped
class PersonService
{
    @Inject
    @field: Default
    lateinit var em: EntityManager

    @Inject
    lateinit var personDAO: PersonDAO

    private val logger: Logger = LoggerFactory.getLogger(PersonService::class.java)

    fun getAllPersons() : List<Person> =
            this.personDAO.listAll()

    @Transactional
    fun addPerson(personDTO: PersonDTO): Person
    {
        val person = personDTO.toPerson()
        this.personDAO.persist(person)
        return person  // now with ID
    }

    fun getPerson(id: Int): Person?
    {
        return this.personDAO.findById(id)
    }

    @Transactional
    fun deletePerson(personId: Int): Boolean
    {
        return this.personDAO.deleteById(personId)
    }

    fun findByAge(ageMin: Int, ageMax: Int): List<Person>
    {
        return this.personDAO.findByAge(ageMin, ageMax)
    }

}