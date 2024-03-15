/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-24
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import net.littlelite.smartquark.model.Person
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class PersonDAO : PanacheRepositoryBase<Person, Int>
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