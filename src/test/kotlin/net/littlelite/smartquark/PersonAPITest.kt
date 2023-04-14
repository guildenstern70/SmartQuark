/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@QuarkusTest
@DisplayName("Person API")
class PersonAPITest
{
    @Test
    @DisplayName("should get one person")
    fun testPersonEndpoint()
    {
        given()
                .`when`().get("/person/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
    }

    @Test
    @DisplayName("should get all persons")
    fun testGetAllPersons()
    {
        given()
                .`when`().get("/person")
                .then()
                .statusCode(200)
    }

    @Test
    @DisplayName("should delete a person")
    fun testDeletePerson()
    {
        given()
                .header("Content-type", "application/json")
                .`when`()
                .delete("/person/1")
                .then()
                .statusCode(200)
    }

    @Test
    @DisplayName("should create a new person")
    fun testCreateNewPerson()
    {
        val requestBody = """
            {
              "age": 20,
              "name": "Giovanna",
              "phones": [
                {
                  "number": "33383208328",
                  "prefix": "+39"
                }
              ],
              "surname": "Pancheri"
            }
        """.trimIndent()

        val response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .`when`()
                .post("/person")
                .then()
                .extract().response()

        assertThat(response.statusCode).isEqualTo(201)
        assertThat(response.jsonPath().getString("name")).isEqualTo("Giovanna")
        assertThat(response.jsonPath().getString("surname")).isEqualTo("Pancheri")

    }


}