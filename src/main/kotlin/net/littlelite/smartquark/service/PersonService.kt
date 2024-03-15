/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-24
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import net.littlelite.smartquark.dao.PersonDAO
import net.littlelite.smartquark.dao.PhoneDAO
import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.dto.PhoneDTO
import net.littlelite.smartquark.model.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional


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

    fun getAllPhones(): List<PhoneDTO> =
        this.phoneDAO.listAll().map { PhoneDTO.fromPhone(it) }

    @Transactional
    fun addPerson(personDTO: PersonDTO): PersonDTO
    {
        val person = personDTO.toPerson()
        this.personDAO.persist(person)
        return PersonDTO.fromPerson(person)
    }

    @Transactional
    fun addPerson(name: String, surname: String, age: Int, phones: Set<PhoneDTO>): Person
    {
        val person = Person(name, surname, age)
        phones.forEach { person.addPhone(it.toPhone()) }
        this.personDAO.persist(person)
        return person  // now with ID
    }

    fun getPersonCount(): Long
    {
        return this.personDAO.count()
    }

    fun getPerson(id: Int): PersonDTO?
    {
        val person = this.personDAO.findById(id) ?: return null
        return PersonDTO.fromPerson(person)
    }

    @Transactional
    fun deletePerson(personId: Int): Boolean
    {
        return this.personDAO.deleteById(personId)
    }

    fun findByAge(ageMin: Int, ageMax: Int): List<PersonDTO>
    {
        return this.personDAO
                .findByAge(ageMin, ageMax)
                .map { PersonDTO.fromPerson(it) }
    }

}