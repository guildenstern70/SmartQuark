/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-22
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import net.littlelite.smartquark.model.Person
import javax.enterprise.context.SessionScoped
import javax.ws.rs.Path

@RegisterForReflection
@Path("/person")
@SessionScoped
data class PersonDTO(
        val id: Int,
        val name: String,
        val surname: String,
        val age: Int,
        val phones: Set<PhoneDTO>
)
{

    /**
     * Empty Constructor for POST action
     */
    constructor(): this(
            -1,
            "",
            "",
            -1,
            setOf())

    fun toPerson(): Person
    {
        val person = Person(this.name, this.surname, this.age)
        person.setPhones(phones.map { it.toPhone() }.toSet())
        return person
    }

    companion object
    {
        fun fromPerson(person: Person): PersonDTO
        {
            val phonesDTO = person.getPhones().map { PhoneDTO.fromPhone(it) }.toSet()
            return PersonDTO(person.id, person.name, person.surname, person.age, phonesDTO)
        }
    }
}
