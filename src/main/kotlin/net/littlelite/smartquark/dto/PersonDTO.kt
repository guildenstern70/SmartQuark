/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import net.littlelite.smartquark.model.Person

@RegisterForReflection
data class PersonDTO(
        val name: String,
        val surname: String,
        val age: Int,
        val phones: Set<PhoneDTO>
)
{

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
            return PersonDTO(person.name, person.surname, person.age, phonesDTO)
        }
    }
}
