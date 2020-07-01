package net.littlelite.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import net.littlelite.model.Person
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class PersonDAO : PanacheRepository<Person>
{
    fun findByName(name: String): List<Person>
    {
        return list("name", name)
    }

    fun findbySurname(surname: String): List<Person>
    {
        return list("surname", surname)
    }

    fun findByAge(ageMin: Int, ageMax:Int): List<Person>
    {
        return find("age <= ?1 and age >= ?2", ageMax, ageMin).list()
    }
}