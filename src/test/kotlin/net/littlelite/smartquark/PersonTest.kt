/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import jakarta.inject.Inject
import net.littlelite.smartquark.dto.PhoneDTO
import net.littlelite.smartquark.service.PersonService
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@QuarkusTest
@DisplayName("Person logic")
class PersonTest
{
    @Inject
    lateinit var personService: PersonService

    @Test
    fun testAlive() {
        RestAssured.given()
            .`when`().get("/alive")
            .then()
            .statusCode(200)
            .body(CoreMatchers.`is`("{ \"alive\": true }"))
    }

    @Test
    @DisplayName("should create person with phones")
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

}