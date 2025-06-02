/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.graphql

import jakarta.inject.Inject
import net.littlelite.smartquark.dto.PhoneDTO
import net.littlelite.smartquark.service.PersonService
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.Query

class PhoneResource
{
    @Inject
    lateinit var personService: PersonService

    @get:Description("Get all phones")
    @get:Query("allPhones")
    val allUsers: List<PhoneDTO>
        get() {
            return personService.getAllPhones()
        }
}