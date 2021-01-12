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
        val age: Int
)
{

    fun toPerson() = Person(this.name, this.surname, this.age)

    companion object
    {
        fun fromPerson(person: Person): PersonDTO
        {
            return PersonDTO(person.name, person.surname, person.age)
        }
    }
}
