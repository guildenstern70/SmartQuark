/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import net.littlelite.smartquark.dao.PersonDAO
import net.littlelite.smartquark.dao.PhoneDAO
import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.dto.PhoneDTO
import net.littlelite.smartquark.dto.PatchPersonDTO
import net.littlelite.smartquark.model.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory


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

    fun getPhonesByPersonId(personId: Int): List<PhoneDTO>?
    {
        val person = this.personDAO.findById(personId) ?: return null
        return person.getPhones().map { PhoneDTO.fromPhone(it) }
    }

    @Transactional
    fun deletePerson(personId: Int): Boolean
    {
        return this.personDAO.deleteById(personId)
    }

    @Transactional
    fun patchPerson(personId: Int, patch: PatchPersonDTO): PersonDTO?
    {
        val person = this.personDAO.findById(personId) ?: return null

        // validation
        patch.age?.let {
            if (it < 0) throw IllegalArgumentException("Age must be >= 0")
        }
        patch.name?.let {
            if (it.isBlank()) throw IllegalArgumentException("Name cannot be blank")
        }
        patch.surname?.let {
            if (it.isBlank()) throw IllegalArgumentException("Surname cannot be blank")
        }

        // apply changes (phones replacement included in PatchPersonDTO.applyTo)
        patch.applyTo(person)

        // entity is managed; changes will be flushed at transaction commit
        return PersonDTO.fromPerson(person)
    }

    @Transactional
    fun putPerson(personId: Int, personDTO: PersonDTO): PersonDTO?
    {
        val person = this.personDAO.findById(personId) ?: return null

        // validation (full replace requires all fields)
        if (personDTO.age < 0) throw IllegalArgumentException("Age must be >= 0")
        if (personDTO.name.isBlank()) throw IllegalArgumentException("Name cannot be blank")
        if (personDTO.surname.isBlank()) throw IllegalArgumentException("Surname cannot be blank")

        // full replace of scalar fields
        person.name = personDTO.name
        person.surname = personDTO.surname
        person.age = personDTO.age

        // replace phones completely
        val existing = person.getPhones().toList()
        existing.forEach { person.removePhone(it) }
        personDTO.phones.forEach { person.addPhone(it.toPhone()) }

        // entity is managed; changes will be flushed at transaction commit
        return PersonDTO.fromPerson(person)
    }

    @Transactional
    fun replacePhones(personId: Int, phones: List<PhoneDTO>): List<PhoneDTO>?
    {
        val person = this.personDAO.findById(personId) ?: return null

        // replace phones completely
        val existing = person.getPhones().toList()
        existing.forEach { person.removePhone(it) }
        phones.forEach { person.addPhone(it.toPhone()) }

        // return updated list as DTOs
        return person.getPhones().map { PhoneDTO.fromPhone(it) }
    }

    fun findByAge(ageMin: Int, ageMax: Int): List<PersonDTO>
    {
        return this.personDAO
                .findByAge(ageMin, ageMax)
                .map { PersonDTO.fromPerson(it) }
    }

}