/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import net.littlelite.smartquark.dao.PersonDAO
import net.littlelite.smartquark.dao.PhoneDAO
import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.dto.PhoneDTO
import net.littlelite.smartquark.model.Person
import net.littlelite.smartquark.model.Phone
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

    @Inject
    lateinit var phoneDAO: PhoneDAO

    private val logger: Logger = LoggerFactory.getLogger(PersonService::class.java)

    fun getAllPersons() : List<PersonDTO> =
        this.personDAO.listAll().map { PersonDTO.fromPerson(it) }

    fun getAllPhones(): List<Phone> =
        this.phoneDAO.listAll()

    @Transactional
    fun addPerson(personDTO: PersonDTO): Person
    {
        val person = personDTO.toPerson()
        this.personDAO.persist(person)
        return person  // now with ID
    }

    @Transactional
    fun addPerson(name: String, surname: String, age: Int, phones: Set<PhoneDTO>): Person
    {
        val person = PersonDTO(name, surname, age, phones).toPerson()
        this.personDAO.persist(person)
        return person  // now with ID
    }

    fun getPersonCount(): Long
    {
        return this.personDAO.count()
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