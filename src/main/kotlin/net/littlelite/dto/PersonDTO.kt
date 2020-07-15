package net.littlelite.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import net.littlelite.model.Person

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
