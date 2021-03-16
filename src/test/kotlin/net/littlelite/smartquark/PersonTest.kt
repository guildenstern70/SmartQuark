/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import net.littlelite.smartquark.dto.PhoneDTO
import net.littlelite.smartquark.service.PersonService
import org.hamcrest.CoreMatchers.startsWith
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class PersonTest
{
    @Inject
    lateinit var personService: PersonService

    @Test
    fun createPersonWithPhones()
    {
        val alessioPhones = setOf(
            PhoneDTO("348", "39290022"),
            PhoneDTO("333", "32233232")
        )
        val savedPerson =
            this.personService.addPerson("Alessio", "Saltarin", 50, alessioPhones)
        val createdPerson = this.personService.getPerson(savedPerson.id)
        assertThat(createdPerson).isNotNull
        assertThat(createdPerson!!.id).isEqualTo(savedPerson.id)
        assertThat(createdPerson.phones.size).isEqualTo(2)
    }

    @Test
    fun testPersonEndpoint()
    {
        given()
                .`when`().get("/person/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
    }

}