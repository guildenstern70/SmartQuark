/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-24
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.graphql

import jakarta.inject.Inject
import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.service.PersonService
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class PersonResource
{
    @Inject
    lateinit var personService: PersonService

    @get:Description("Get all persons")
    @get:Query("allPersons")
    val allUsers: List<PersonDTO>
        get() {
            return personService.getAllPersons()
        }
}