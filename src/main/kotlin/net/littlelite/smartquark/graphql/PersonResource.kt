/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.graphql

import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.service.PersonService
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class PersonResource(private val personService: PersonService)
{
    @get:Description("Get all persons")
    @get:Query("allPersons")
    val allUsers: List<PersonDTO>
        get() {
            return personService.getAllPersons()
        }
}